package me.cxis.algorithms.tree.btree.example1;

import java.util.ArrayList;
import java.util.List;

public class BTree {

    /**
     * 4阶B树
     */
    private static final int M = 4;


    /**
     * 每个节点最多有M个子树，最多有M-1个关键字
     * 除了根节点和叶子节点外，每个节点至少有 (M/2) 个子树，至少有(M/2)-1个关键字，其中(M/2)需要向上取整
     */
    private static class BTNode {

        /**
         * 节点中关键字个数
         */
        private int keyNum;

        /**
         * 父节点
         */
        private BTNode parent;

        /**
         * 节点中的关键字
         * 第0个不使用
         */
        private List<Integer> keys = new ArrayList<>(M + 1);

        /**
         * 子节点
         */
        private List<BTNode> children = new ArrayList<>(M + 1);

        public int getKeyNum() {
            return keyNum;
        }

        public void setKeyNum(int keyNum) {
            this.keyNum = keyNum;
        }

        public BTNode getParent() {
            return parent;
        }

        public void setParent(BTNode parent) {
            this.parent = parent;
        }

        public List<Integer> getKeys() {
            return keys;
        }

        public void setKeys(List<Integer> keys) {
            this.keys = keys;
        }

        public List<BTNode> getChildren() {
            return children;
        }

        public void setChildren(List<BTNode> children) {
            this.children = children;
        }
    }




}
