package me.cxis.algorithms.tree.avltree.example2;

public class AVLNode<T extends Comparable> {

    /**
     * 左结点
     */
    private AVLNode<T> left;

    /**
     * 右结点
     */
    private AVLNode<T> right;

    /**
     * 数据
     */
    private T data;

    /**
     * 当前结点的高度
     */
    private int height;

    public AVLNode(T data) {
        this(data, null, null);
    }

    public AVLNode(T data, AVLNode<T> left, AVLNode<T> right) {
        this(data, left, right, 0);
    }

    public AVLNode(T data, AVLNode<T> left, AVLNode<T> right, int height) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.height = height;
    }

    public AVLNode<T> getLeft() {
        return left;
    }

    public void setLeft(AVLNode<T> left) {
        this.left = left;
    }

    public AVLNode<T> getRight() {
        return right;
    }

    public void setRight(AVLNode<T> right) {
        this.right = right;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "AVLNode{" +
            "left=" + left +
            ", right=" + right +
            ", data=" + data +
            ", height=" + height +
            '}';
    }
}
