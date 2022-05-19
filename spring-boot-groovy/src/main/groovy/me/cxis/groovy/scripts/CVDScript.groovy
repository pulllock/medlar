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

        }

        if (diabetes && !isMen && smoker) {

        }

        if (!diabetes && isMen && !smoker) {

        }

        if (!diabetes && isMen && smoker) {

        }

        if (!diabetes && !isMen && !smoker) {

        }

        if (!diabetes && !isMen && smoker) {

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
            [],
            [],
            [],
            [],
            [],
            [],
            []
    ]

    static int[][][] diabetes_women_no_smoker = [
            [],
            [],
            [],
            [],
            [],
            [],
            []
    ]

    static int[][][] diabetes_women_smoker = [
            [],
            [],
            [],
            [],
            [],
            [],
            []
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
