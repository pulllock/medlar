package me.cxis.unit.test.spock.manager;

import org.springframework.stereotype.Component;

@Component
public class CvdManager {

    public int calculateCvdRisk(boolean isMen, int age, boolean diabetes, boolean smoker, int sbp, float tchol) {
        if (diabetes && isMen && !smoker) {
            int[][] tt = diabetes_men_non_smoker[getAgeIndex(age)];
            int[] t = tt[getSbpIndex(sbp)];
            return t[getTchlIndex(tchol)];
        }

        if (diabetes && isMen && smoker) {
            int[][] tt = diabetes_men_smoker[getAgeIndex(age)];
            int[] t = tt[getSbpIndex(sbp)];
            return t[getTchlIndex(tchol)];
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
        return 0;
    }

    static int getAgeIndex(int age) {
        if (age >= 40 && age <= 44) {
            return 6;
        } else if (age >= 45 && age <= 49) {
            return 5;
        } else if (age >= 50 && age <= 54) {
            return 4;
        } else if (age >= 55 && age <= 59) {
            return 3;
        } else if (age >= 60 && age <= 64) {
            return 2;
        } else if (age >= 65 && age <= 69) {
            return 1;
        } else if (age >= 70 && age <= 74) {
            return 0;
        } else {
            return 0;
        }
    }

    static int getSbpIndex(int sbp) {
        if (sbp < 120) {
            return 4;
        } else if (sbp>= 120 && sbp <= 139) {
            return 3;
        } else if (sbp >= 140 && sbp <= 159) {
            return 2;
        } else if (sbp >= 160 && sbp <= 179) {
            return 1;
        } else if (sbp >= 180) {
            return 0;
        }
        return 0;
    }

    static int getTchlIndex(float tchl) {
        if (tchl < 4) {
            return 0;
        } else if (tchl >= 4 && tchl <= 4.9) {
            return 1;
        } else if (tchl >= 5 && tchl <= 5.9) {
            return 2;
        } else if (tchl >= 6 && tchl <= 6.9) {
            return 3;
        } else if (tchl >= 7) {
            return 4;
        }
        return 0;
    }

    static int[][][] diabetes_men_non_smoker = {
            {
                    {26,29,31,34,37},
                    {23,25,27,29,32},
                    {19,21,23,25,27},
                    {16,18,19,21,23},
                    {14,15,17,18,20}
            },
            {
                    {23,25,27,29,32},
                    {19,21,23,25,27},
                    {16,17,19,21,23},
                    {13,15,16,18,19},
                    {11,12,13,15,16}
            },
            {
                    {19,21,23,26,29},
                    {16,18,19,21,24},
                    {13,14,16,18,20},
                    {11,12,13,15,16},
                    {9,10,11,12,13}
            },
            {
                    {17,18,20,22,25},
                    {13,15,16,18,21},
                    {11,12,13,15,17},
                    {9,10,11,12,14},
                    {7,8,9,10,11}
            },
            {
                    {14,16,17,19,22},
                    {11,12,14,16,18},
                    {9,10,11,12,14},
                    {7,8,9,10,11},
                    {6,6,7,8,9}
            },
            {
                    {12, 14, 15, 17, 19},
                    {10, 11, 12, 13, 15},
                    {7, 8, 9, 10, 12},
                    {6, 6, 7, 8, 9},
                    {4, 5, 6, 6, 7}
            },
            {
                    {11, 12, 13, 15, 17},
                    {8, 9, 10, 11, 13},
                    {6, 7, 8, 9, 10},
                    {5, 5, 6, 7, 8},
                    {4, 4, 4, 5, 6}
            }
    };

    static int[][][] diabetes_men_smoker = {
            {},
            {},
            {},
            {},
            {},
            {},
            {}
    };
}
