package me.cxis.algorithms.tree.binarysearchtree.example1;

public class BinaryNode {

    private int value;

    private BinaryNode left;

    private BinaryNode right;

    private BinaryNode parent;

    public BinaryNode(int value, BinaryNode left, BinaryNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinaryNode getLeft() {
        return left;
    }

    public void setLeft(BinaryNode left) {
        this.left = left;
    }

    public BinaryNode getRight() {
        return right;
    }

    public void setRight(BinaryNode right) {
        this.right = right;
    }

    public BinaryNode getParent() {
        return parent;
    }

    public void setParent(BinaryNode parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                ", parent=" + parent +
                '}';
    }
}
