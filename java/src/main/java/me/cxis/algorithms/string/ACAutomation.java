package me.cxis.algorithms.string;

import java.util.*;

/**
 * AC自动机
 */
public class ACAutomation {

    /**
     * 只处理英文类型的字符，长度为128
     */
    private final static int LENGTH = 128;

    /**
     * 根节点，根节点为空，不存储任何信息
     */
    private Node root;

    /**
     * 模式串集合
     */
    private List<String> targets;

    private ACAutomation(){}

    /**
     * 构造AC自动机
     * @param targets 用来构造自动机的模式串
     */
    public ACAutomation(List<String> targets) {
        // 初始化根节点
        root = new Node();

        this.targets = targets;

        // 构造字典树
        buildTrieTree();

        // 构造AC自动机
        buildACAutomation();
    }

    /**
     * 构造Trie树
     */
    private void buildTrieTree() {
        for (String target : targets) {
            Node current = root;
            for (int i = 0; i < target.length(); i++) {
                    char ch = target.charAt(i);
                    // root结点或者当前结点中不包含当前字符ch，创建一个新节点，添加
                    if (current.table[ch] == null) {
                        current.table[ch] = new Node();
                    }

                    // 新节点变为当前结点，继续往前走
                    current = current.table[ch];
            }

            // 每个target模式串处理完后，需要将最后一个结点设置为终结点
            current.str = target;
        }
    }

    /**
     * 构建AC自动机
     * 找每个节点的失配结点
     */
    private void buildACAutomation() {
        // 队列，用来记录要处理的结点
        LinkedList<Node> queue = new LinkedList<>();

        // 单独处理根结点的子节点，根节点的子结点的fail指针都指向根结点
        for (Node node : root.table) {
            if (node != null) {
                node.fail = root;

                // 将根结点的子节点加入队列中，下面挨个处理
                queue.addLast(node);
            }
        }

        // 挨个处理队列中的结点
        while (!queue.isEmpty()) {
            // 处理当前结点的子结点的fail指针
            Node parent = queue.removeFirst();
            
            // 挨个子结点处理
            for (int i = 0; i < parent.table.length; i++) {
                Node child = parent.table[i];
                if (child != null) {
                    // 将子结点入队列，稍后还需要继续处理子结点的子结点
                    queue.addLast(child);

                    // 从parent的fail指针开始处理
                    Node fail = parent.fail;
                    while (true) {
                        // fail为null表示找到了根节点都没有找到
                        if (fail == null) {
                            // child的fail指针指向根节点
                            child.fail = root;
                            break;
                        }

                        // 能够找到fail结点，并且fail结点的子节点中存在i
                        if (fail.table[i] != null) {
                            child.fail = fail.table[i];
                            break;
                        }

                        // 没有找到，继续找fail的fail结点
                        fail = fail.fail;
                    }
                }
            }
        }
    }

    /**
     * 从文本串中查找有哪些模式串
     * @param text 要查找的文本串
     * @return 表示在文本字符串中查找的结果，key：模式串，value：模式串在文本字符串中的位置
     */
    public Map<String, List<Integer>> search(String text) {
        Map<String, List<Integer>> results = new HashMap<>();

        for (String target : targets) {
            results.put(target, new LinkedList<>());
        }

        Node current = root;
        int i = 0;
        while (i < text.length()) {
            // 要查找的字符
            char ch = text.charAt(i);

            // 要查找的字符在当前结点的子节点中存在
            if (current.table[ch] != null) {
                // 继续从子节点找下个字符串
                current = current.table[ch];

                // 子节点变成当前结点后，看下当前节点是不是终结点，如果是终结点，说明找到了一个匹配的模式串
                if (current.str != null) {
                    // 将找到的这个模式串和模式串在文本串中的位置加入到结果中
                    results.get(current.str).add(i - current.str.length() + 1);
                }

                // TODO
                if (current.fail != null && current.str != null) {
                    results.get(current.fail.str).add(i - current.fail.str.length() + 1);
                }

                i++;
            }

            // 要查找的字符在当前结点的子结点中不存在，需要找当前结点的fail节点，看fail结点的子节点是否是要找的字符
            else {
                current = current.fail;
                // 一直找到了根结点，没找到
                if (current == null) {
                    // 重新从根节点开始，找下一个字符
                    current = root;
                    i++;
                }
            }
        }

        return results;
    }

    private static class Node {

        /**
         * 如果当前结点是是一个终结节点，此字段表示一个模式串，即从根节点到当前结点之间的字符组成的一个模式串
         */
        String str;

        /**
         * 存储节点的子节点
         */
        Node[] table = new Node[LENGTH];

        /**
         * fail结点，当前结点指向的fail结点
         * 当前节点的子节点如果不能匹配文本字符串中的某个字符的时候，fail指向了下一个应该查找的结点
         */
        Node fail;

    }

    public static void main(String[] args) {
        List<String> targets = new ArrayList<>();
        targets.add("she");
        targets.add("he");
        targets.add("say");
        targets.add("shr");
        targets.add("her");

        String text = "yasherhs";

        ACAutomation acAutomation = new ACAutomation(targets);
        System.out.println(acAutomation.search(text));
    }
}
