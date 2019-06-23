package me.cxis.algorithms.tree.binarysearchtree.example1;

public class BinaryNode<E> {

    private E value;

    private BinaryNode<E> left;

    private BinaryNode<E> right;

    public BinaryNode(E value, BinaryNode left, BinaryNode right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public BinaryNode<E> getLeft() {
        return left;
    }

    public void setLeft(BinaryNode<E> left) {
        this.left = left;
    }

    public BinaryNode<E> getRight() {
        return right;
    }

    public void setRight(BinaryNode<E> right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinaryNode{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
