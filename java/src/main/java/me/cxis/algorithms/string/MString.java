package me.cxis.algorithms.string;

public class MString {

    /**
     * 匹配字符串，朴素模式（Brute-Force）
     * @param origin
     * @param pattern
     * @return
     */
    public boolean contains(String origin, String pattern) {
        // 主串
        char[] a = origin.toCharArray();
        // 字串
        char[] b = pattern.toCharArray();

        int i = 0;
        int j = 0;
        while (i < a.length - 1 && j < b.length - 1) {

            if (a[i] == b[j]) {
                // 对应位置的字符相等，继续比较下一个字符
                i++;
                j++;
            } else {
                // 对应位置的字符不相等，主串的位置回到上一次比较位置+1
                i = i - j + 1;
                // 字串位置重头开始匹配
                j = 0;
            }
        }
        // 匹配成功
        if (j == b.length - 1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String origin = "00000000000000000000000000000000000000000000000000001";
        String pattern = "00000001";
        MString mString = new MString();
        System.out.println(mString.contains(origin, pattern));
    }
}
