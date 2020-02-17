package me.cxis.algorithms.tree.huffman.example1;

import java.util.Arrays;

public class HuffmanTree {

    private int start;

    /**
     * 构造huffman树
     * @param nodes 外节点数组
     * @return 包含内外节点，且建立了连接关系的节点数组
     */
    public Node[] buildTree(Node[] nodes) {
        // 获取nodes长度
        int n = nodes.length;

        // 构建一个长度为2n - 1的数组，前n个节点存放外节点，后n个存放内节点
        int newLength = 2 * n - 1;
        Node[] huffmanNodes = new Node[newLength];

        // huffmanNodes中节点权重初始化为0
        for (int i = 0; i < newLength; i++) {
            huffmanNodes[i] = new Node(0);
        }

        // 将nodes中的外节点，赋值给huffmanNodes前n个结点
        for (int i = 0; i < n; i++) {
            huffmanNodes[i].setData(nodes[i].getData());
            huffmanNodes[i].setWeight(nodes[i].getWeight());
        }

        // 计算内节点，并建立连接关系
        for (int i = n; i < newLength; i++) {
            // 获取最小节点的索引
            int min1 = select(huffmanNodes, i, 0);
            // 获取次小节点的索引
            int min2 = select(huffmanNodes, i, 1);

            huffmanNodes[i].setLeft(min1);
            huffmanNodes[i].setRight(min2);

            huffmanNodes[min1].setParent(i);
            huffmanNodes[min2].setParent(i);

            huffmanNodes[i].setWeight(huffmanNodes[min1].getWeight() + huffmanNodes[min2].getWeight());

            start += 2;
        }

        return huffmanNodes;
    }

    private int select(Node[] huffmanNodes, int range, int rank) {
        // TODO wrong
        System.out.println("huffmanNodes: " + Arrays.asList(huffmanNodes));
        // 将huffmanNodes中从0到range中的元素拷贝出来
        Node[] subNodes = Arrays.copyOf(huffmanNodes, range);
        // 拷贝元素进行排序，使用快排
        QuickSort sort = new QuickSort();
        sort.quickSort(subNodes);

        // 排序之后找到新的起点，就是之前已经处理过的最小和次小需要跳过
        Node target = subNodes[rank + start];
        for (int i = 0; i < huffmanNodes.length; i++) {
            if (target == huffmanNodes[i]) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        Node[] nodes = new Node[4];
        nodes[0] = new Node("a", 7);
        nodes[1] = new Node("b", 5);
        nodes[2] = new Node("c", 2);
        nodes[3] = new Node("d", 4);

        System.out.println(Arrays.asList(nodes));

        HuffmanTree huffmanTree = new HuffmanTree();
        Node[] result = huffmanTree.buildTree(nodes);
        System.out.println(Arrays.asList(result));
    }

    class QuickSort {
        public void quickSort(Node[] array) {
            quickSort(array, 0, array.length - 1);
        }

        private void quickSort(Node[] array, int left, int right) {
            if (left < right) {
                // 最左边的作为基准值
                int pivot = array[left].getWeight();
                int i = left;
                int j = right;
                while (i < j) {
                    while (i < j && array[j].getWeight() > pivot) {
                        j--;
                    }

                    if (i < j) {
                        array[i] = array[j];
                        i++;
                    }

                    while (i < j && array[i].getWeight() < pivot) {
                        i++;
                    }

                    if (i < j) {
                        array[j] = array[i];
                        j--;
                    }
                }

                array[i].setWeight(pivot);
                quickSort(array, left, i - 1);
                quickSort(array, i + 1, right);
            }
        }
    }

}
