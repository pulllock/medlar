package me.cxis.algorithms.tree.binarysearchtree.example2;

public class BinarySearchTree<T extends Comparable<? super T>> {

    private BinaryNode<T> root;

    public BinaryNode<T> search(T data) {
        return search(root, data);
    }

    private BinaryNode<T> search(BinaryNode<T> node, T data) {
        if (node == null) {
            return null;
        }

        if (data.compareTo(node.getData()) < 0) {
            return search(node.getLeft(), data);
        } else if (data.compareTo(node.getData()) > 0) {
            return search(node.getRight(), data);
        } else {
            return node;
        }
    }

    public BinaryNode<T> search1(T data) {
        BinaryNode<T> node = root;
        while (node != null) {
            if (data.compareTo(node.getData()) < 0) {
                node = node.getLeft();
            } else if (data.compareTo(node.getData()) > 0) {
                node = node.getRight();
            } else {
                ;
            }
        }
        return node;
    }

    public BinaryNode<T> insert(T data) {
        if (root == null) {
            root = new BinaryNode<>(data);
            return root;
        }
        return insert(root, data);
    }

    private BinaryNode<T> insert(BinaryNode<T> node, T data) {
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                BinaryNode<T> newNode = new BinaryNode<>(data);
                node.setLeft(newNode);
                newNode.setParent(node);
                return newNode;
            }
            return insert(node.getLeft(), data);
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                BinaryNode<T> newNode = new BinaryNode<>(data);
                node.setRight(newNode);
                newNode.setParent(node);
                return newNode;
            }
            return insert(node.getRight(), data);
        } else {
            return node;
        }
    }

    public BinaryNode<T> delete(T data) {
        return delete(root, data);
    }

    private BinaryNode<T> delete(BinaryNode<T> node, T data) {
        if (data.compareTo(node.getData()) < 0) {
            if (node.getLeft() == null) {
                return null;
            }
            return delete(node.getLeft(), data);
        } else if (data.compareTo(node.getData()) > 0) {
            if (node.getRight() == null) {
                return null;
            }
            return delete(node.getRight(), data);
        } else {
            BinaryNode<T> old = node;
            if (node.getLeft() != null && node.getRight() != null) {
                // 找到right中最小的
            } else if (node.getLeft() != null) {
                node.getLeft().setParent(node.getParent());
                node.getParent().setLeft(node.getLeft());
            } else if (node.getRight() != null) {
                node.getRight().setParent(node.getParent());
                node.getParent().setRight(node.getRight());
            } else {
                BinaryNode<T> parent = node.getParent();
                if (node == parent.getLeft()) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }
            }
            return old;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        tree.insert(8);
        tree.insert(3);
        tree.insert(6);
        tree.insert(7);
        tree.insert(4);
        System.out.println(tree.root);
        System.out.println(tree.search(4));
        System.out.println(tree.search(5));
    }

}
