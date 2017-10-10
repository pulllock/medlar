package me.cxis.algorithms.tree.threadedbinarytree;

/**
 * Created by cheng.xi on 2017-05-18 23:40.
 */
public class Node {
    private int data;//数据
    private Node left;//左结点
    private Node right;//右结点
    private boolean leftThread;//true 表示指向该结点的前驱，false表示指向结点的左孩子
    private boolean rightThread;//true 表示指向该结点的后继，false表示指向结点的右孩子

    public Node(int data){
        this(data,null,null,true,true);
    }

    public Node(int data,Node left,Node right, boolean leftThread, boolean rightThread){
        this.data = data;
        this.left = left;
        this.right = right;
        this.leftThread = leftThread;
        this.rightThread = rightThread;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public boolean isLeftThread() {
        return leftThread;
    }

    public void setLeftThread(boolean leftThread) {
        this.leftThread = leftThread;
    }

    public boolean isRightThread() {
        return rightThread;
    }

    public void setRightThread(boolean rightThread) {
        this.rightThread = rightThread;
    }
}
