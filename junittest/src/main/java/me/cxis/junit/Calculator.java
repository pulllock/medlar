package me.cxis.junit;

public class Calculator {

    public int evaluate(String expression) {
        int sum = 0;
        for (String summand: expression.split("\\+")) {
            sum += Integer.valueOf(summand);
        }

        return sum;
    }

    public int add(int a, int b) {
        System.out.println("=======正在执行加法");
        return a + b;
    }

    public int minus(int a, int b) {
        System.out.println("=======正在执行减法");
        return a - b;
    }

    public int square(int n) {
        System.out.println("=======正在执行平方计算");
        return n * n;
    }

    //Bug : 死循环
    public void squareRoot(int n) {
        System.out.println("=======正在执行死循环的方法");
        for(; ;)
            ;
    }

    public int multiply(int a, int b) {
        System.out.println("=======正在执行乘法");
        return a * b;
    }

    public int divide(int a, int b) throws Exception {
        System.out.println("=======正在执行除法");
        if (0 == b) {
            throw new Exception("除数不能为零");
        }
        return a / b;
    }
}
