package me.cxis.algorithms.string;

/**
 * BF算法，暴力算法（Brute Force)，普通模式匹配算法
 */
public class BFString {

    public static void main(String[] args) {
        String pattern = "ssfass";
        String target = "asdfsaasfssfasseerer";
        int result = bfMatch(pattern, target);
        System.out.println(result);
    }

    /**
     * BF算法
     * @param pattern 模式串
     * @param target 目标串
     * @return
     */
    private static int bfMatch(String pattern, String target) {
        int i = 0;
        int j = 0;
        String s = "";
        while (j < pattern.length() && i < target.length()) {
            if (pattern.charAt(j) == target.charAt(i)) {
                s = s + target.charAt(i);
                i++;
                j++;
            } else {
                i  = i - j + 1;
                j = 0;
                s = "";
            }
        }

        if (j == pattern.length()) {
            System.out.println(s);
            return i - pattern.length();
        }

        return -1;
    }
}
