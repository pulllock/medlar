package me.cxis.algorithms.tree.avltree.example2;

public class AVLTree<T extends Comparable> {

    /**
     * 根节点
     */
    private AVLNode<T> root;

    /**
     * 插入
     * @param data
     */
    public void insert(T data) {
        if (data == null) {
            throw new RuntimeException("null data not allowed");
        }

        this.root = insert(data, root);
    }

    /**
     * 删除
     * @param data
     */
    public void remove(T data) {
        if (data == null) {
            throw new RuntimeException("null data not allowed");
        }
        this.root = remove(data, root);
    }

    /**
     * 删除
     * @param data
     * @param node
     * @return
     */
    private AVLNode<T> remove(T data, AVLNode<T> node) {
        if (node == null) {
            return null;
        }

        int com = data.compareTo(node.getData());
        if (com < 0) {
            // 左子树
            node.setLeft(remove(data, node.getLeft()));
            // 检测是否失衡
            if (height(node.getRight()) - height(node.getLeft()) == 2) {
                if (height(node.getRight().getLeft()) > height(node.getRight().getRight())) {
                    // 右左双旋转RL
                    node = rightLeftRotate(node);
                } else {
                    // 右右旋转（RR）左单旋
                    node = rightRightRotate(node);
                }
            }
        } else if (com > 0) {
            // 右子树
            node.setRight(remove(data, node.getRight()));
            // 检测是否失衡
            if (height(node.getLeft()) - height(node.getRight()) == 2) {
                if (height(node.getLeft().getRight()) > height(node.getLeft().getLeft())) {
                    // 左右双旋转LR
                    node = leftRightRotate(node);
                } else {
                    // 左左旋转（LL）又单旋
                    node = leftLeftRotate(node);
                }
            }
        } else {
            // 找到了要删除的点
            if (node.getRight() != null && node.getLeft() != null) {
                // 要删除的节点有两个子节点
                // 找到一个替换节点
                node.setData(findMin(node.getRight()).getData());

                // 删除用于替换的那个节点
                node.setRight(remove(node.getData(), node.getRight()));
            } else {
                // 要删除的节点只有一个子节点或者一个子节点都没有
                node = (node.getLeft() != null) ? node.getLeft() : node.getRight();
            }
        }

        // 更新高度
        if (node != null) {
            node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        }
        return node;
    }

    /**
     * 查找最小结点
     * @param node
     * @return
     */
    private AVLNode<T> findMin(AVLNode<T> node) {
        if (node == null) {
            return null;
        } else if (node.getLeft() == null) {
            return node;
        } else {
            return findMin(node.getLeft());
        }
    }

    /**
     * 插入
     * @param data
     * @param node
     * @return
     */
    private AVLNode<T> insert(T data, AVLNode<T> node) {
        // 没有子节点，创建新节点插入
        if (node == null) {
            node = new AVLNode<>(data);
        } else if (data.compareTo(node.getData()) < 0) {
            // 左子树插入
            node.setLeft(insert(data, node.getLeft()));
            // 左子树插入，左子树高度肯定比右子树高度高
            if (height(node.getLeft()) - height(node.getRight()) == 2) {
                if (data.compareTo(node.getLeft().getData()) < 0) {
                    // 左左旋转（LL）
                    node = leftLeftRotate(node);
                } else {
                    node = leftRightRotate(node);
                }
            }
        } else if (data.compareTo(node.getData()) > 0) {
            // 右子树插入
            node.setRight(insert(data, node.getRight()));
            // 右子树插入，右子树高度肯定比左子树高度高
            if (height(node.getRight()) - height(node.getLeft()) == 2) {
                if (data.compareTo(node.getRight().getData()) > 0) {
                    node = rightRightRotate(node);
                } else {
                    node = rightLeftRotate(node);
                }
            }
        } else {

        }

        // 计算结点的高度
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        return node;
    }


    /**
     * 左左旋转（LL），右单旋
     * @param node 失衡的结点
     * @return
     */
    private AVLNode<T> leftLeftRotate(AVLNode<T> node) {
        // 将失衡的结点的左子树先赋值给n
        AVLNode<T> n = node.getLeft();

        // 将n的右子树赋值给失衡结点的左子树
        node.setLeft(n.getRight());

        // 将失衡结点变为n的右子树
        n.setRight(node);

        // 重新计算node和n的高度
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        n.setHeight(Math.max(height(n.getLeft()), node.getHeight()) + 1);

        // 返回新的根节点
        return n;
    }

    /**
     * 右右旋转（RR），左单旋
     * @param node 失衡的节点
     * @return
     */
    private AVLNode<T> rightRightRotate(AVLNode<T> node) {
        // 将失衡的节点的右子树先赋值给n
        AVLNode<T> n = node.getRight();

        // 将n的左子树赋值给失衡结点的右子树
        node.setRight(n.getLeft());

        // 将失衡结点变为n的左子树
        n.setLeft(node);

        // 重新计算node和n的高度
        node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
        n.setHeight(Math.max(node.getHeight(), height(n.getRight())) + 1);

        // 返回新的根节点
        return n;
    }

    /**
     * 左右（LR）双旋
     * @param node
     * @return
     */
    private AVLNode<T> leftRightRotate(AVLNode<T> node) {
        // 失衡结点node的左子树先进行一个RR旋转
        AVLNode<T> n = rightRightRotate(node.getLeft());

        // 将失衡结点的左子树修改为RR旋转后的结点
        node.setLeft(n);

        // 对失衡结点node进行LL旋转
        return leftLeftRotate(node);
    }

    /**
     * 右左（RL）双旋
     * @param node
     * @return
     */
    private AVLNode<T> rightLeftRotate(AVLNode<T> node) {
        // 失衡点node的右子树先进行一个LL旋转
        AVLNode<T> n = leftLeftRotate(node.getRight());

        // 将失衡结点的右子树修改为LL旋转后的结点
        node.setRight(n);

        // 对失衡结点进行RR旋转
        return rightRightRotate(node);
    }

    /**
     * 计算结点的高度
     * @param node
     * @return
     */
    private int height(AVLNode<T> node) {
        return node == null ? -1 : node.getHeight();
    }
}
