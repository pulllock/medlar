package me.cxis.algorithms.tree.avltree.example1;

public class AVLTree {

    private AVLTreeNode root;

    public AVLTree() {
    }

    /**
     * 左左单旋转
     * @param k2 失衡点
     * @return 旋转后的根节点
     */
    public AVLTreeNode leftLeftRotation(AVLTreeNode k2) {
        AVLTreeNode k1;

        k1 = k2.getLeft();
        k2.setLeft(k1.getRight());
        k1.setRight(k2);

        k2.setHeight(Math.max(height(k2.getLeft()), height(k2.getRight())) + 1);
        k1.setHeight(Math.max(height(k1.getLeft()), k2.getHeight()) + 1);
        return k1;
    }

    /**
     * 右右单旋转
     * @param k1 失衡点
     * @return 旋转后的根节点
     */
    public AVLTreeNode rightRightRotation(AVLTreeNode k1) {
        AVLTreeNode k2;

        k2 = k1.getRight();
        k1.setRight(k2.getLeft());
        k2.setLeft(k1);

        k1.setHeight(Math.max(height(k1.getLeft()), height(k1.getRight())) + 1);
        k2.setHeight(Math.max(height(k2.getRight()), k1.getHeight()) + 1);
        return k2;
    }

    /**
     * 左右旋转
     * 先右右旋转，变成了左失衡，继续进行左左旋转
     * @param k3
     * @return 旋转后的根节点
     */
    public AVLTreeNode leftRightRotation(AVLTreeNode k3) {
        k3.setLeft(rightRightRotation(k3.getLeft()));
        return leftLeftRotation(k3);
    }

    /**
     * 右左旋转
     * 先左左旋转，变成了右失衡，继续进行右右旋转
     * @param k1
     * @return
     */
    public AVLTreeNode rightLeftRotation(AVLTreeNode k1) {
        k1.setRight(leftRightRotation(k1.getRight()));
        return rightRightRotation(k1);
    }

    public int height(AVLTreeNode node) {
        if (node == null) {
            return 0;
        }

        return node.getHeight();
    }

    public int height() {
        return height(root);
    }

    public AVLTreeNode getRoot() {
        return root;
    }

    public void setRoot(AVLTreeNode root) {
        this.root = root;
    }
}
