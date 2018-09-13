package me.cxis.gof.builder_pattern;

public class Client {

    public static void main(String[] args) {
        Builder builder = new ConcreteBuilder(); // 可通过配置文件实现
        Director director = new Director(builder);
        director.construct();
    }
}
