package me.cxis.algorithms.tree.huffman.example1;

public class Code {

    /**
     * 字符串
     */
    private String data;

    /**
     * 存放编码后的字符串
     */
    private String bit;

    public Code(String data, String bit) {
        this.data = data;
        this.bit = bit;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getBit() {
        return bit;
    }

    public void setBit(String bit) {
        this.bit = bit;
    }

    @Override
    public String toString() {
        return "Code{" +
                "data='" + data + '\'' +
                ", bit='" + bit + '\'' +
                '}';
    }
}
