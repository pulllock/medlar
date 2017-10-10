package me.cxis.algorithms.tree.binarysearchtree;

/**
 * Created by cheng.xi on 2017-05-18 22:21.
 */
public class Main {
    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(34);
        binarySearchTree.insert(12);
        binarySearchTree.insert(45);
        binarySearchTree.insert(25);
        binarySearchTree.insert(56);
        binarySearchTree.insert(4);
        binarySearchTree.insert(67);

        System.out.println("displayPreOrder:");
        binarySearchTree.displayPreOrder(binarySearchTree.root);
        System.out.println();

        System.out.println("displayInOrder:");
        binarySearchTree.displayInOrder(binarySearchTree.root);
        System.out.println();

        System.out.println("displayPostOrder:");
        binarySearchTree.displayPostOrder(binarySearchTree.root);
        System.out.println();

        System.out.println("查找存在的：25");
        System.out.println(binarySearchTree.find(25));

        System.out.println("查找不存在的：100");
        System.out.println(binarySearchTree.find(100));
    }
}
