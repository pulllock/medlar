package me.cxis.algorithms.string.kmp.example1;

public class KMPString {

    public static void main(String[] args) {
        String pattern = "ssfass";
        String target = "asdfsaasfssfasseerer";

        int indexOfTarget = kmpString(target, pattern);
        System.out.println("index of target: " + indexOfTarget);
    }

    private static int kmpString(String target, String pattern) {
        int i = 0;
        int j = 0;

        Integer[] next = getNext(pattern);

        while (i < target.length() && j < pattern.length()) {
            if (j == -1 || target.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }

        if (j == pattern.length()) {
            return i - pattern.length();
        }
        return -1;
    }

    private static Integer[] getNext(String pattern) {
        Integer[] next = new Integer[pattern.length()];
        next[0] = -1;

        int j = 0;
        int k = -1;
        while (j < pattern.length() - 1) {
            if (k == -1 || pattern.charAt(j) == pattern.charAt(k)) {
                j++;
                k++;
                next[j] = k;
            } else {
                k = next[k];
            }
        }

        return next;
    }
}
