package me.cxis.algorithms.tree.binarysearchtree.example2;

public class BinaryNode<T extends Comparable<? super T>> {

    private T data;

    private BinaryNode<T> left;

    private BinaryNode<T> right;

    private BinaryNode<T> parent;

    public BinaryNode(T data) {
        this(data, null, null, null);
    }

    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right, BinaryNode<T> parent) {
        this.data = data;
        this.left = left;
        this.right = right;
        this.parent = parent;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public BinaryNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryNode<T> left) {
        this.left = left;
    }

    public BinaryNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryNode<T> right) {
        this.right = right;
    }

    public BinaryNode<T> getParent() {
        return parent;
    }

    public void setParent(BinaryNode<T> parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "data=" + data +
                ", left=" + left +
                ", right=" + right +
                ", parent=" + parent +
                '}';
    }
}
