package me.cxis.algorithms;

/**
 * Created by 程熙 on 7/20/16.
 * 截取字符串
 */
public class SplitString {

    public static String splitString(String str,int length) throws Exception {
        if(null == str){
            throw new Exception("输入字符串为空");
        }
        int byNum = 0;
        byte[] bt = str.getBytes();
        byNum = bt.length;
        if(length > byNum){
            length = byNum;
        }
        if(bt[length] < 0){
            str = new String(bt,0,--length);
        }else {
            str = new String(bt,0,length);
        }
        return str;
    }

    public static void main(String[] args) {
        try {
            System.out.println(splitString("我ABDC汉DHFh",4));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
