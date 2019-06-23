package me.cxis.algorithms.tree.binarysearchtree.example1;

public class BinarySortTree<E> {

    private BinaryNode<E> root;

    public BinarySortTree() {
    }

    public BinaryNode<E> search(E value) {
        if (value == null || !(value instanceof Comparable)) {
            return null;
        }

        BinaryNode<E> p = root;

        while(p != null && ((Comparable) value).compareTo(p.getValue()) != 0) {
            if (((Comparable) value).compareTo(p.getValue()) < 0) {
                p = p.getLeft();
            } else {
                p = p.getRight();
            }
        }

        return p;
    }

    public boolean insert(E value) {
        if (value == null || !(value instanceof Comparable)) {
            return false;
        }

        if (root == null) {
            root = new BinaryNode<>(value, null, null);
            return true;
        }

        return insert(value, root);
    }

    public boolean insert(E value, BinaryNode<E> root) {
        Comparable<E> comparable = (Comparable) value;
        if (comparable.compareTo(root.getValue()) == 0) {
            return false;
        }

        if (comparable.compareTo(root.getValue()) < 0) {
            if (root.getLeft() == null) {
                root.setLeft(new BinaryNode<>(value, null, null));
                return true;
            } else {
                return insert(value, root.getLeft());
            }
        } else {
            if (root.getRight() == null) {
                root.setRight(new BinaryNode<>(value, null, null));
                return true;
            } else {
                return insert(value, root.getRight());
            }
        }
    }

    public void inOrder() {
        System.out.println("InOrder:");
        inOrder(root);

    }

    private void inOrder(BinaryNode<E> root) {
        if (root != null) {
            inOrder(root.getLeft());
            System.out.println(root.getValue());
            inOrder(root.getRight());
        }
    }

    public static void main(String[] args) {
        BinarySortTree<Integer> tree = new BinarySortTree<>();
        int[] values = {54, 18, 66, 87, 36, 12, 54, 81, 15, 57, 6, 40, 99, 85};

        for (int i = 0; i < values.length; i++) {
            tree.insert(values[i]);
        }

        tree.inOrder();

        System.out.println(tree.search(40));
        System.out.println(tree.search(87));
        System.out.println(tree.search(1));
    }
}
