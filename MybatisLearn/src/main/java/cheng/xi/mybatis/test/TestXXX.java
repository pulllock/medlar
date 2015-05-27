package cheng.xi.mybatis.test;

/**
 * Created by justdoit on 15-5-8.
 */
public class TestXXX {

    /*int a = b + 1;
    static int b = 2;*/

    /*static int count = 2;
    static {
        System.out.println("StaticInitTest-----静态初始化");
        name = "静态块中的name";
    }
    static String name = "初始化的Name";

    public  static void main(String args[]){
        System.out.println("'count:" + TestXXX.count);
        System.out.println("'name:" + TestXXX.name);
    }*/

    /*public void test(){

    }*/

    public static void main(String args[]){
        C c = new C();

        B b = c;

        A a = c;
        System.out.println(c.count);
        System.out.println(b.count);
        System.out.println(a.count);

    }

}
