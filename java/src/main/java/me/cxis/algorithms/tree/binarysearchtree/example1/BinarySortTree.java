package me.cxis.algorithms.tree.binarysearchtree.example1;

public class BinarySortTree {

    private BinaryNode root;

    public BinarySortTree() {
    }

    /**
     * 查找，非递归
     * @param value
     * @return
     */
    public BinaryNode search(int value) {

        BinaryNode p = root;

        while(p != null && value != p.getValue()) {
            if (value < p.getValue()) {
                p = p.getLeft();
            } else {
                p = p.getRight();
            }
        }

        return p;
    }

    /**
     * 查找，递归
     * @param root
     * @param value
     * @return
     */
    public BinaryNode search(BinaryNode root, int value) {

        if (root == null) {
            return null;
        }

        if (value < root.getValue()) {
            return search(root.getLeft(), value);
        } else if (value > root.getValue()) {
            return search(root.getRight(), value);
        } else {
            return root;
        }
    }

    /**
     * 插入，递归
     * @param value
     * @return
     */
    public boolean insert(int value) {
        if (root == null) {
            root = new BinaryNode(value, null, null);
            return true;
        }

        return insert(value, root);
    }

    /**
     * 插入，递归
     * @param value
     * @param root
     * @return
     */
    public boolean insert(int value, BinaryNode root) {
        if (value == root.getValue()) {
            return false;
        }

        if (value < root.getValue()) {
            if (root.getLeft() == null) {
                root.setLeft(new BinaryNode(value, null, null));
                return true;
            } else {
                return insert(value, root.getLeft());
            }
        } else {
            if (root.getRight() == null) {
                root.setRight(new BinaryNode(value, null, null));
                return true;
            } else {
                return insert(value, root.getRight());
            }
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        System.out.println("InOrder:");
        inOrder(root);

    }

    /**
     * 中序遍历
     * @param root
     */
    private void inOrder(BinaryNode root) {
        if (root != null) {
            inOrder(root.getLeft());
            System.out.println(root.getValue());
            inOrder(root.getRight());
        }
    }

    public void preOrder() {
        System.out.println("preOrder:");
        preOrder(root);
    }

    private void preOrder(BinaryNode root) {
        if (root != null) {
            System.out.println(root.getValue());
            inOrder(root.getLeft());
            inOrder(root.getRight());
        }
    }

    public void postOrder() {
        System.out.println("postOrder:");
        postOrder(root);
    }

    private void postOrder(BinaryNode root) {
        if (root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            System.out.println(root.getValue());
        }
    }

    public BinaryNode getRoot() {
        return root;
    }

    public static void main(String[] args) {
        BinarySortTree tree = new BinarySortTree();
        int[] values = {54, 18, 66, 87, 36, 12, 54, 81, 15, 57, 6, 40, 99, 85};

        for (int i = 0; i < values.length; i++) {
            tree.insert(values[i]);
        }

        tree.inOrder();

        tree.preOrder();

        tree.postOrder();

        System.out.println(tree.getRoot());

        System.out.println(tree.search(40));
        System.out.println(tree.search(tree.getRoot(), 40));
        System.out.println(tree.search(87));
        System.out.println(tree.search(tree.getRoot(), 87));
        System.out.println(tree.search(1));
        System.out.println(tree.search(tree.getRoot(), 1));
    }
}
