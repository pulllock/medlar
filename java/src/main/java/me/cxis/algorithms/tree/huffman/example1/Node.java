package me.cxis.algorithms.tree.huffman.example1;

public class Node {

    /**
     * 结点中数据
     */
    private String data;

    /**
     * 结点权重值
     */
    private int weight;

    /**
     * 左子节点
     */
    private int left;

    /**
     * 右子节点
     */
    private int right;

    /**
     * 父节点
     */
    private int parent;

    public Node(int weight) {
        this(null, weight);
    }

    public Node(String data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "Node{" +
            "data='" + data + '\'' +
            ", weight=" + weight +
            ", left=" + left +
            ", right=" + right +
            ", parent=" + parent +
            '}';
    }
}
