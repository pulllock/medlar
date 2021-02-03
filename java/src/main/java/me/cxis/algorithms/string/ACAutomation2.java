package me.cxis.algorithms.string;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * AC自动机
 * 1. 构建一颗字典树
 * 2. 构造失败指针
 * 3. 实现查询功能
 */
public class ACAutomation2 {

    /**
     * 存储模式串
     */
    private List<String> targets;

    /**
     * 根节点
     */
    private Node root;

    private ACAutomation2(){}

    public ACAutomation2(List<String> targets) {
        this.targets = targets;

        root = new Node();

        // 构造字典树
        buildTrieTree();

        // 构造失败指针
        buildACAutomation();
    }

    private void buildTrieTree() {
        for (String target : targets) {
            Node current = root;
            for (int i = 0; i < target.length(); i++) {
                char ch = target.charAt(i);
                Node[] children = current.children;
                if (children[ch] == null) {
                    children[ch] = new Node();
                }
                current = children[ch];
            }

            current.wordLengthList.add(target.length());
        }
    }

    private void buildACAutomation() {
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            Node[] children = current.children;
            for (int i = 0; i < children.length; i++) {
                if (children[i] == null) {
                    continue;
                }

                Node temp = current.fail;
                while (temp != null && temp.children[i] == null) {
                    temp = temp.fail;
                }

                if (temp == null) {
                    children[i].fail = root;
                } else {
                    children[i].fail = temp.children[i];
                }

                children[i].wordLengthList.addAll(children[i].fail.wordLengthList);
                queue.offer(children[i]);
            }
        }
    }

    public void search(String text) {
        Node current = root;
        for (int i = 0; i < text.length(); i++) {
            char ch = text.charAt(i);
            Node next = current.children[ch];
            if (next != null) {
                current = next;
                handleMatchWords(current, text, i);
            } else {
                next = current.fail;
                while (next != null && next.children[ch] == null) {
                    next = next.fail;
                }

                if (next == null) {
                    current = root;
                } else {
                    current = next.children[ch];
                    handleMatchWords(current, text, i);
                }
            }
        }
    }

    private void handleMatchWords(Node node, String word, int currentPos)
    {
        for (Integer wordLen : node.wordLengthList)
        {
            int startIndex = currentPos - wordLen + 1;
            String matchWord = word.substring(startIndex, currentPos + 1);
            System.out.println(matchWord);
        }
    }

    static class Node {
        /**
         * 英文字符，128位
         */
        Node[] children = new Node[128];

        /**
         * 失败指针指向的结点
         */
        Node fail;

        /**
         * 如果到当前结点为止，是一个完整的模式串，这里记录这个模式串的长度
         */
        List<Integer> wordLengthList = new LinkedList<>();
    }

    public static void main(String[] args) {
        List<String> targets = new ArrayList<>();
        targets.add("he");
        targets.add("she");
        targets.add("hers");
        targets.add("his");
        targets.add("shy");

        ACAutomation2 acAutomation2 = new ACAutomation2(targets);
        acAutomation2.search("ahishers");
    }
}
