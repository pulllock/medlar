package me.cxis.algorithms.tree.binarysearchtree;

/**
 * Created by cheng.xi on 2017-05-18 22:02.
 */
public class BinarySearchTree {
    public static Node root;

    public BinarySearchTree(){
        this.root = null;
    }

    /**
     * 插入操作
     * @param key
     */
    public void insert(int key){
        Node newNode = new Node(key);

        if(root == null){
            root = newNode;
            return;
        }

        Node currentNode = root;
        Node parentNode = null;

        while(true){
            parentNode = currentNode;

            if(key < currentNode.getData()){
                currentNode = currentNode.getLeft();
                if(currentNode == null){
                    parentNode.setLeft(newNode);
                    return;
                }
            }else {
                currentNode = currentNode.getRight();
                if(currentNode == null){
                    parentNode.setRight(newNode);
                    return;
                }
            }
        }
    }



    /**
     * 前序遍历
     * @param root
     */
    public void displayPreOrder(Node root){
        if(root != null){
            System.out.print(" " + root.getData());
            displayPreOrder(root.getLeft());
            displayPreOrder(root.getRight());
        }
    }

    /**
     * 中序遍历
     * @param root
     */
    public void displayInOrder(Node root){
        if(root != null){
            displayInOrder(root.getLeft());
            System.out.print(" " + root.getData());
            displayInOrder(root.getRight());
        }
    }

    /**
     * 后序遍历
     * @param root
     */
    public void displayPostOrder(Node root){
        if(root != null){
            displayPostOrder(root.getLeft());
            displayPostOrder(root.getRight());
            System.out.print(" " + root.getData());
        }
    }

    /**
     * 查找操作
     * @param key
     * @return
     */
    public boolean find(int key){
        Node current = root;
        while(current != null){
            if(current.getData() == key){
                return true;
            }else if(current.getData() < key){
                current = current.getRight();
            }else {
                current = current.getLeft();
            }
        }
        return false;
    }


    //TODO 递归插入

    //TODO 删除元素
}
