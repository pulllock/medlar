package me.cxis.stringandfinal;

/**
 * Created by cheng.xi on 2017-06-06 12:05.
 */
public class Main {
    public static final String VISIT_PRE = "VISIT_PRE_";
    public static String VISIT_PRE_1 = "VISIT_PRE_";

    public static void main(String[] args) {
        System.out.println(VISIT_PRE.hashCode());
        System.out.println(VISIT_PRE_1.hashCode());
        System.out.println(VISIT_PRE == VISIT_PRE_1);

        String s = VISIT_PRE + "x";
        String s1 = VISIT_PRE_1 + "x";
        System.out.println(s.hashCode());
        System.out.println(s1.hashCode());
        System.out.println(s);
        System.out.println(s1);
        System.out.println(s == s1);
        
    }
}
