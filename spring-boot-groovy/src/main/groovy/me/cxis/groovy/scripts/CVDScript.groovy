package me.cxis.groovy.scripts

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString
import groovy.transform.TupleConstructor
import me.cxis.groovy.engine.AbstractResultCalculator
import me.cxis.groovy.engine.ScriptContext

class CVDScript extends AbstractResultCalculator {

    @Override
    String calculate(ScriptContext context) {
        println("context: " + context)
        if (!context || !context.bizResultId || !context.param) {
            return null
        }

        def param = new JsonSlurper().parseText(context.param)
        def user = new User(*:param)
        def diabetes = user.diabetes
        def isMen = user.isMen
        def smoker = user.smoker
        def age = user.age
        def sbp = user.sbp
        def tchol = user.tchol

        if (diabetes && isMen && !smoker) {
            int[][] tt = diabetes_men_non_smoker[getAgeIndex(age)]
            int[] t = tt[getSbpIndex(sbp)]
            return t[getTchlIndex(tchol)]
        }

        if (diabetes && isMen && smoker) {
            int[][] tt = diabetes_men_smoker[getAgeIndex(age)]
            int[] t = tt[getSbpIndex(sbp)]
            return t[getTchlIndex(tchol)]
        }

        if (diabetes && !isMen && !smoker) {
            int[][] tt = diabetes_women_no_smoker[getAgeIndex(age)]
            int[] t = tt[getSbpIndex(sbp)]
            return t[getTchlIndex(tchol)]
        }

        if (diabetes && !isMen && smoker) {
            int[][] tt = diabetes_women_smoker[getAgeIndex(age)]
            int[] t = tt[getSbpIndex(sbp)]
            return t[getTchlIndex(tchol)]
        }

        if (!diabetes && isMen && !smoker) {
            int[][] tt = without_diabetes_men_no_smoker[getAgeIndex(age)]
            int[] t = tt[getSbpIndex(sbp)]
            return t[getTchlIndex(tchol)]
        }

        if (!diabetes && isMen && smoker) {
            int[][] tt = without_diabetes_men_smoker[getAgeIndex(age)]
            int[] t = tt[getSbpIndex(sbp)]
            return t[getTchlIndex(tchol)]
        }

        if (!diabetes && !isMen && !smoker) {
            int[][] tt = without_diabetes_women_no_smoker[getAgeIndex(age)]
            int[] t = tt[getSbpIndex(sbp)]
            return t[getTchlIndex(tchol)]
        }

        if (!diabetes && !isMen && smoker) {
            int[][] tt = without_diabetes_women_smoker[getAgeIndex(age)]
            int[] t = tt[getSbpIndex(sbp)]
            return t[getTchlIndex(tchol)]
        }
        return 0
    }

    static int getAgeIndex(int age) {
        def index = 6

        switch (age) {
            case 40..44:
                index = 6
                break
            case 45..49:
                index = 5
                break
            case 50..54:
                index = 4
                break
            case 55..59:
                index = 3
                break
            case 60..64:
                index = 2
                break
            case 65..69:
                index = 1
                break
            case 70..74:
                index = 0
                break
            default:
                index = 6
                break
        }
    }

    static int getSbpIndex(int sbp) {
        if (sbp < 120) {
            return 4
        } else if (sbp>= 120 && sbp <= 139) {
            return 3
        } else if (sbp >= 140 && sbp <= 159) {
            return 2
        } else if (sbp >= 160 && sbp <= 179) {
            return 1
        } else if (sbp >= 180) {
            return 0
        }
        return 0
    }

    static int getTchlIndex(float tchl) {
        if (tchl < 4) {
            return 0
        } else if (tchl >= 4 && tchl <= 4.9) {
            return 1
        } else if (tchl >= 5 && tchl <= 5.9) {
            return 2
        } else if (tchl >= 6 && tchl <= 6.9) {
            return 3
        } else if (tchl >= 7) {
            return 4
        }
        return 0
    }

    static int[][][] diabetes_men_non_smoker = [
            [
                    [26,29,31,34,37],
                    [23,25,27,29,32],
                    [19,21,23,25,27],
                    [16,18,19,21,23],
                    [14,15,17,18,20]
            ],
            [
                    [23,25,27,29,32],
                    [19,21,23,25,27],
                    [16,17,19,21,23],
                    [13,15,16,18,19],
                    [11,12,13,15,16]
            ],
            [
                    [19,21,23,26,29],
                    [16,18,19,21,24],
                    [13,14,16,18,20],
                    [11,12,13,15,16],
                    [9,10,11,12,13]
            ],
            [
                    [17,18,20,22,25],
                    [13,15,16,18,21],
                    [11,12,13,15,17],
                    [9,10,11,12,14],
                    [7,8,9,10,11]
            ],
            [
                    [14,16,17,19,22],
                    [11,12,14,16,18],
                    [9,10,11,12,14],
                    [7,8,9,10,11],
                    [6,6,7,8,9]
            ],
            [
                    [12, 14, 15, 17, 19],
                    [10, 11, 12, 13, 15],
                    [7, 8, 9, 10, 12],
                    [6, 6, 7, 8, 9],
                    [4, 5, 6, 6, 7]
            ],
            [
                    [11, 12, 13, 15, 17],
                    [8, 9, 10, 11, 13],
                    [6, 7, 8, 9, 10],
                    [5, 5, 6, 7, 8],
                    [4, 4, 4, 5, 6]
            ]
    ]

    static int[][][] diabetes_men_smoker = [
            [
                    [32, 35, 38, 41, 44],
                    [28, 30, 32, 35, 38],
                    [24, 26, 28, 30, 33],
                    [20, 22, 24, 26, 29],
                    [17, 19, 20, 22, 24]
            ],
            [
                    [29, 31, 34, 38, 41],
                    [24, 27, 29, 32, 35],
                    [21, 22, 25, 27, 30],
                    [17, 19, 21, 23, 25],
                    [14, 16, 17, 19, 21]
            ],
            [
                    [26, 28, 31, 35, 38],
                    [22, 24, 26, 29, 32],
                    [18, 20, 22, 24, 27],
                    [15, 16, 18, 20, 23],
                    [12, 13, 15, 17, 19]
            ],
            [
                    [23, 26, 29, 32, 36],
                    [19, 21, 24, 26, 30],
                    [16, 17, 19, 22, 24],
                    [13, 14, 16, 18, 20],
                    [10, 11, 13, 14, 16]
            ],
            [
                    [21, 23, 26, 29, 33],
                    [17, 19, 21, 24, 27],
                    [13, 15, 17, 19, 22],
                    [11, 12, 14, 15, 18],
                    [8, 10, 11, 12, 14]
            ],
            [
                    [19, 21, 24, 27, 31],
                    [15, 17, 19, 22, 25],
                    [12, 13, 15, 17, 20],
                    [9, 10, 12, 13, 16],
                    [7, 8, 9, 11, 12]
            ],
            [
                    [17, 19, 22, 25, 29],
                    [13, 15, 17, 19, 23],
                    [10, 11, 13, 15, 18],
                    [8, 9, 10, 12, 14],
                    [6, 7, 8, 9, 11]
            ]
    ]

    static int[][][] diabetes_women_no_smoker = [
            [
                    [26, 27, 29, 31, 33],
                    [21, 23, 24, 26, 27],
                    [18, 19, 20, 21, 23],
                    [15, 16, 17, 18, 19],
                    [12, 13, 14, 14, 15]
            ],
            [
                    [20, 22, 24, 25, 27],
                    [17, 18, 19, 21, 22],
                    [13, 14, 16, 17, 18],
                    [11, 12, 13, 14, 15],
                    [9, 9, 10, 11, 12]
            ],
            [
                    [16, 18, 19, 21, 23],
                    [13, 14, 15, 17, 18],
                    [10, 11, 12, 13, 14],
                    [8, 9, 10, 10, 11],
                    [6, 7, 8, 8, 9]
            ],
            [
                    [13, 14, 15, 17, 19],
                    [10, 11, 12, 13, 15],
                    [8, 8, 9, 10, 11],
                    [6, 7, 7, 8, 9],
                    [5, 5, 6, 6, 7]
            ],
            [
                    [10, 11, 12, 14, 16],
                    [8, 9, 10, 11, 12],
                    [6, 7, 7, 8, 9],
                    [4, 5, 5, 6, 7],
                    [3, 4, 4, 5, 5]
            ],
            [
                    [8, 9, 10, 11, 13],
                    [6, 7, 8, 8, 10],
                    [5, 5, 6, 6, 7],
                    [3, 4, 4, 5, 5],
                    [2, 3, 3, 3, 4]
            ],
            [
                    [7, 7, 8, 9, 11],
                    [5, 5, 6, 7, 8],
                    [3, 4, 4, 5, 6],
                    [3, 3, 3, 4, 4],
                    [2, 2, 2, 3, 3]
            ]
    ]

    static int[][][] diabetes_women_smoker = [
            [
                    [37, 39, 42, 44, 47],
                    [31, 33, 35, 38, 40],
                    [26, 28, 30, 32, 34],
                    [22, 23, 25, 26, 28],
                    [18, 19, 20, 22, 23]
            ],
            [
                    [32, 35, 37, 40, 44],
                    [27, 29, 31, 34, 36],
                    [22, 24, 25, 28, 30],
                    [18, 19, 21, 23, 25],
                    [14, 16, 17, 18, 20]
            ],
            [
                    [28, 31, 34, 37, 40],
                    [23, 25, 27, 30, 33],
                    [18, 20, 22, 24, 27],
                    [15, 16, 17, 19, 21],
                    [12, 13, 14, 15, 17]
            ],
            [
                    [25, 27, 30, 33, 37],
                    [19, 21, 24, 27, 30],
                    [15, 17, 19, 21, 24],
                    [12, 13, 15, 16, 18],
                    [9, 10, 11, 13, 14]
            ],
            [
                    [21, 24, 27, 30, 34],
                    [16, 18, 21, 23, 27],
                    [13, 14, 16, 18, 21],
                    [10, 11, 12, 14, 16],
                    [7, 8, 9, 11, 12]
            ],
            [
                    [19, 21, 24, 27, 31],
                    [14, 16, 18, 21, 24],
                    [10, 12, 14, 16, 18],
                    [8, 9, 10, 12, 14],
                    [6, 7, 8, 9, 10]
            ],
            [
                    [16, 18, 21, 24, 28],
                    [12, 14, 16, 18, 22],
                    [9, 10, 12, 14, 16],
                    [6, 7, 9, 10, 12],
                    [5, 5, 6, 7, 9]
            ]
    ]

    static int[][][] without_diabetes_men_no_smoker = [
            [
                    [21, 22, 24, 26, 28],
                    [18, 19, 21, 22, 24],
                    [15, 16, 17, 19, 21],
                    [13, 14, 15, 16, 18],
                    [11, 12, 13, 14, 15]
            ],
            [
                    [17, 18, 20, 22, 24],
                    [14, 15, 17, 18, 20],
                    [12, 13, 14, 15, 17],
                    [10, 11, 12, 13, 14],
                    [8, 9, 10, 11, 12]
            ],
            [
                    [14, 15, 16, 18, 20],
                    [11, 12, 13, 15, 16],
                    [9, 10, 11, 12, 13],
                    [7, 8, 9, 10, 11],
                    [6, 7, 7, 8, 9]
            ],
            [
                    [11, 12, 13, 15, 16],
                    [9, 10, 11, 12, 13],
                    [7, 8, 9, 10, 11],
                    [6, 6, 7, 8, 9],
                    [5, 5, 6, 6, 7]
            ],
            [
                    [9, 10, 11, 12, 14],
                    [7, 8, 9, 10, 11],
                    [6, 6, 7, 8, 9],
                    [4, 5, 5, 6, 7],
                    [3, 4, 4, 5, 5]
            ],
            [
                    [7, 8, 9, 10, 11],
                    [6, 6, 7, 8, 9],
                    [4, 5, 5, 6, 7],
                    [3, 4, 4, 5, 5],
                    [3, 3, 3, 4, 4]
            ],
            [
                    [6, 7, 7, 8, 9],
                    [5, 5, 6, 6, 7],
                    [3, 4, 4, 5, 5],
                    [3, 3, 3, 4, 4],
                    [2, 2, 2, 3, 3]
            ]
    ]

    static int[][][] without_diabetes_men_smoker = [
            [
                    [25, 27, 29, 32, 34],
                    [21, 23, 25, 27, 30],
                    [18, 20, 21, 23, 25],
                    [15, 17, 18, 20, 22],
                    [13, 14, 15, 17, 18]
            ],
            [
                    [21, 23, 25, 28, 30],
                    [18, 20, 21, 23, 26],
                    [15, 16, 18, 20, 22],
                    [13, 14, 15, 17, 18],
                    [10, 11, 13, 14, 15]
            ],
            [
                    [18, 20, 22, 24, 27],
                    [15, 17, 18, 20, 22],
                    [12, 14, 15, 17, 19],
                    [10, 11, 12, 14, 15],
                    [8, 9, 10, 11, 13]
            ],
            [
                    [16, 17, 19, 21, 24],
                    [13, 14, 15, 17, 19],
                    [10, 11, 13, 14, 16],
                    [8, 9, 10, 11, 13],
                    [7, 7, 8, 9, 10]
            ],
            [
                    [13, 15, 16, 18, 21],
                    [11, 12, 13, 15, 17],
                    [8, 9, 10, 12, 13],
                    [7, 7, 8, 9, 11],
                    [5, 6, 7, 7, 8]
            ],
            [
                    [11, 13, 14, 16, 18],
                    [9, 10, 11, 13, 14],
                    [7, 8, 9, 10, 11],
                    [5, 6, 7, 8, 9],
                    [4, 5, 5, 6, 7]
            ],
            [
                    [10, 11, 12, 14, 16],
                    [7, 8, 9, 11, 12],
                    [6, 6, 7, 8, 10],
                    [4, 5, 5, 6, 7],
                    [3, 4, 4, 5, 6]
            ]
    ]

    static int[][][] without_diabetes_women_no_smoker = [
            [
                    [16, 17, 18, 19, 21],
                    [13, 14, 15, 16, 17],
                    [11, 12, 12, 13, 14],
                    [9, 10, 10, 11, 11],
                    [8, 8, 8, 9, 9]
            ],
            [
                    [12, 13, 14, 15, 16],
                    [10, 10, 11, 12, 13],
                    [8, 8, 9, 10, 10],
                    [6, 7, 7, 8, 8],
                    [5, 5, 6, 6, 7]
            ],
            [
                    [9, 10, 10, 11, 12],
                    [7, 8, 8, 9, 10],
                    [6, 6, 6, 7, 8],
                    [4, 5, 5, 5, 6],
                    [3, 4, 4, 4, 5]
            ],
            [
                    [7, 7, 8, 8, 9],
                    [5, 6, 6, 7, 7],
                    [4, 4, 5, 5, 6],
                    [3, 3, 4, 4, 4],
                    [2, 3, 3, 3, 3]
            ],
            [
                    [5, 5, 6, 6, 7],
                    [4, 4, 4, 5, 5],
                    [3, 3, 3, 4, 4],
                    [2, 2, 3, 3, 3],
                    [2, 2, 2, 2, 2]
            ],
            [
                    [4, 4, 4, 5, 5],
                    [3, 3, 3, 4, 4],
                    [2, 2, 2, 3, 3],
                    [2, 2, 2, 2, 2],
                    [1, 1, 1, 1, 2]
            ],
            [
                    [3, 3, 3, 4, 4],
                    [2, 2, 2, 3, 3],
                    [1, 2, 2, 2, 2],
                    [1, 1, 1, 1, 2],
                    [1, 1, 1, 1, 1]
            ]
    ]

    static int[][][] without_diabetes_women_smoker = [
            [
                    [24, 25, 27, 28, 30],
                    [20, 21, 22, 24, 25],
                    [16, 17, 18, 20, 21],
                    [14, 14, 15, 16, 17],
                    [11, 12, 12, 13, 14]
            ],
            [
                    [19, 21, 22, 24, 26],
                    [16, 17, 18, 19, 21],
                    [13, 14, 15, 16, 17],
                    [10, 11, 12, 13, 14],
                    [8, 9, 9, 10, 11]
            ],
            [
                    [15, 17, 18, 20, 22],
                    [12, 13, 14, 16, 17],
                    [10, 11, 11, 13, 14],
                    [8, 8, 9, 10, 11],
                    [6, 7, 7, 8, 9]
            ],
            [
                    [12, 14, 15, 17, 18],
                    [10, 11, 12, 13, 14],
                    [8, 8, 9, 10, 11],
                    [6, 6, 7, 8, 9],
                    [4, 5, 5, 6, 7]
            ],
            [
                    [10, 11, 12, 14, 16],
                    [8, 8, 9, 11, 12],
                    [6, 6, 7, 8, 9],
                    [4, 5, 5, 6, 7],
                    [3, 4, 4, 5, 5]
            ],
            [
                    [8, 9, 10, 11, 13],
                    [6, 7, 8, 9, 10],
                    [4, 5, 6, 6, 7],
                    [3, 4, 4, 5, 5],
                    [2, 3, 3, 3, 4]
            ],
            [
                    [7, 7, 8, 10, 11],
                    [5, 5, 6, 7, 8],
                    [3, 4, 4, 5, 6],
                    [2, 3, 3, 4, 4],
                    [2, 2, 2, 3, 3]
            ]
    ]

    @EqualsAndHashCode
    @ToString
    @TupleConstructor
    class User {
        boolean diabetes
        boolean isMen
        boolean smoker
        int age
        int sbp
        float tchol
    }

    static void main(String[] args) {
        def map = [diabetes: true, isMen: true, smoker: false,
                age: 70, sbp: 119, tchol: 3]

        def result = new CVDScript().calculate(new ScriptContext(bizResultId: 1 as long, param: JsonOutput.toJson(map)))
        println(result)
    }
}
