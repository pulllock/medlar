package me.cxis.algorithms.linearlist;

/**
 * Created by cheng.xi on 2017-05-31 20:53.
 * 判断表达式中圆括号是否匹配
 */
public class ExpBracket {
    public static String isValid(String expstr){
        SeqStack<String> stack = new SeqStack<>(expstr.length());
        int i = 0;
        while(i < expstr.length()) {
            char ch = expstr.charAt(i);
            i++;
            switch (ch) {
                case '(':
                    stack.push(ch + "");
                    break;
                case ')':
                    if (stack.isEmpty() || !stack.pop().equals("(")) {
                        return "期望（";
                    }
            }
        }
            if(stack.isEmpty()){
                return "";
            }else {
                return "期望）";
            }
    }

    public static void main(String[] args) {
        System.out.println(isValid("((1+2)*3+4)"));
        System.out.println(isValid("((1+2)*3+4"));
        System.out.println(isValid("((1+2)*3+4))("));
    }
}
