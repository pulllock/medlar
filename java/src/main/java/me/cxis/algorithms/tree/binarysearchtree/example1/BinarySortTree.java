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

    /**
     * 前序遍历
     */
    public void preOrder() {
        System.out.println("preOrder:");
        preOrder(root);
    }

    /**
     * 前序遍历
     * @param root
     */
    private void preOrder(BinaryNode root) {
        if (root != null) {
            System.out.println(root.getValue());
            inOrder(root.getLeft());
            inOrder(root.getRight());
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        System.out.println("postOrder:");
        postOrder(root);
    }

    /**
     * 后序遍历
     * @param root
     */
    private void postOrder(BinaryNode root) {
        if (root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            System.out.println(root.getValue());
        }
    }

    /**
     * 最大值
     * @return
     */
    public BinaryNode max() {
        return max(root);
    }

    private BinaryNode max(BinaryNode node) {
        if (node == null) {
            return null;
        }

        while (node.getRight() != null) {
            node = node.getRight();
        }
        return node;
    }

    /**
     * 最小值
     * @return
     */
    public BinaryNode min() {
        return min(root);
    }

    private BinaryNode min(BinaryNode node) {
        if (node == null) {
            return null;
        }

        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    /**
     * 前驱，node的左子树中的最大值
     * @param node
     * @return
     */
    public BinaryNode predecessor(BinaryNode node) {
        if (node.getLeft() != null) {
            return max(node.getLeft());
        }

        // 如果x没有左孩子，有两种可能
        // x是一个右孩子，x的前驱就是它的父节点
        // x是一个左孩子，x的前驱就是x的最低父节点，并且该父节点需要有右孩子
        BinaryNode p =  node.getParent();

        while ((p != null) && (node == p.getLeft())) {
            node = p;
            p = p.getParent();
        }

        return node;
    }

    /**
     * 后继，node的右子树的最小值
     * @param node
     * @return
     */
    public BinaryNode successor(BinaryNode node) {
        if (node.getRight() != null) {
            return min(node.getRight());
        }

        // 如果x没有右孩子，有两种可能
        // x是一个左孩子，x的后继就是他的父节点
        // x是一个右孩子，x的后继就是x的最低父节点，并且该父节点需要有左孩子
        BinaryNode p = node.getParent();

        while ((p != null) && (node == p.getRight())) {
            node = p;
            p = p.getParent();
        }

        return node;
    }

    public boolean delete(int value) {

        BinaryNode parent = root;
        BinaryNode current = root;

        boolean isLeftChild = false;

        while (current.getValue() != value) {
            parent = current;
            if (current.getValue() > value) {
                isLeftChild = true;
                current = current.getLeft();
            } else {
                isLeftChild = false;
                current = current.getRight();
            }

            if (current == null) {
                return false;
            }
        }

        // 如果要删除的节点没有子节点
        if (current.getLeft() == null && current.getRight() == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.setLeft(null);
            } else {
                parent.setRight(null);
            }
        } else if (current.getRight() == null) {
            // 要删除的节点只有一个左子节点
            if (current == root) {
                root = current.getLeft();
            } else if (isLeftChild) {
                parent.setLeft(current.getLeft());
            } else {
                parent.setRight(current.getLeft());
            }
        } else if (current.getLeft() == null) {
            // 要删除的节点只有一个右子节点
            if (current == root) {
                root = current.getRight();
            } else if (isLeftChild) {
                parent.setLeft(current.getRight());
            } else {
                parent.setRight(current.getRight());
            }
        } else if (current.getRight() != null && current.getLeft() != null) {
            // 要删除的节点有两个子节点
            // 查找右子树中最小的

            BinaryNode successor = successor(current);

            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.setLeft(successor);
            } else {
                parent.setRight(successor);
            }

            successor.setLeft(current.getLeft());
        }
        return true;
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

        System.out.println("root:");
        System.out.println(tree.getRoot());

        System.out.println(tree.search(40));
        System.out.println(tree.search(tree.getRoot(), 40));
        System.out.println(tree.search(87));
        System.out.println(tree.search(tree.getRoot(), 87));
        System.out.println(tree.search(1));
        System.out.println(tree.search(tree.getRoot(), 1));

        System.out.println("max:");
        System.out.println(tree.max());
        System.out.println("min:");
        System.out.println(tree.min());

        tree.delete(40);

        tree.inOrder();

        tree.preOrder();

        tree.postOrder();

        System.out.println("root:");
        System.out.println(tree.getRoot());

        System.out.println(tree.search(40));
        System.out.println(tree.search(tree.getRoot(), 40));
        System.out.println(tree.search(87));
        System.out.println(tree.search(tree.getRoot(), 87));
        System.out.println(tree.search(1));
        System.out.println(tree.search(tree.getRoot(), 1));

        System.out.println("max:");
        System.out.println(tree.max());
        System.out.println("min:");
        System.out.println(tree.min());
    }
}
