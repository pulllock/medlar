package me.cxis.algorithms.string;

/**
 * BF算法，暴力算法（Brute Force)，普通模式匹配算法
 */
public class BFString {

    public static void main(String[] args) {
        String target = "ssfass";
        String model = "asdfsaasfssfasseerer";
        int result = bfMatch(target, model);
        System.out.println(result);
    }

    /**
     * BF算法
     * @param target 目标串
     * @param model 模式串
     * @return
     */
    private static int bfMatch(String target, String model) {
        int i = 0;
        int j = 0;
        String s = "";
        while (i < target.length() && j < model.length()) {
            if (target.charAt(i) == model.charAt(j)) {
                s = s + model.charAt(j);
                i++;
                j++;
            } else {
                j  = j - i + 1;
                i = 0;
                s = "";
            }
        }

        if (i == target.length()) {
            System.out.println(s);
            return j - target.length();
        }

        return -1;
    }
}
