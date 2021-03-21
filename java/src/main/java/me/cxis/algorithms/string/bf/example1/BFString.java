package me.cxis.algorithms.string.bf.example1;

public class BFString {

    public static void main(String[] args) {
        String pattern = "ssfass";
        String target = "asdfsaasfssfasseerer";

        int indexOfTarget = bfString(target, pattern);
        System.out.println("index of target: " + indexOfTarget);
    }

    private static int bfString(String target, String pattern) {
        int i = 0;
        int j = 0;

        while (i < target.length() && j < pattern.length()) {
            if (target.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }

        if (j == pattern.length()) {
            return i - pattern.length();
        }

        return -1;
    }
}
