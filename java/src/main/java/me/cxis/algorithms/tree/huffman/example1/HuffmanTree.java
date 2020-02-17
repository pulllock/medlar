package me.cxis.algorithms.tree.huffman.example1;

import java.util.Arrays;

public class HuffmanTree {

    private int start;

    public HuffmanTree() {
        start = 0;
    }

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

    /**
     * 赫夫曼编码
     * @param nodes
     * @return
     */
    public Code[] encode(Node[] nodes) {
        Node[] huffmanTree = buildTree(nodes);
        int n = nodes.length;

        Code[] code = new Code[n];
        String bit;
        for (int i = 0; i < n; i++) {
            bit = "";
            int current = i;
            int parent = huffmanTree[i].getParent();
            while (parent != 0) {
                if (huffmanTree[parent].getLeft() == current) {
                    bit = "0" + bit;
                } else {
                    bit = "1" + bit;
                }
                current = parent;
                parent = huffmanTree[parent].getParent();
            }
            code[i] = new Code(huffmanTree[i].getData(), bit);
        }
        return code;
    }

    /**
     * 解码
     * @param nodes
     * @param code
     * @return
     */
    public String decode(Node[] nodes, String code) {
        String result = "";
        Node[] huffmanTree = buildTree(nodes);
        int n = huffmanTree.length - 1;
        for (int i = 0; i < code.length(); i++) {
            char c = code.charAt(i);
            if (c == '1') {
                n = huffmanTree[n].getRight();
            } else {
                n= huffmanTree[n].getLeft();
            }

            if (huffmanTree[n].getLeft() == 0) {
                result += huffmanTree[n].getData();
                n = huffmanTree.length - 1;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Node[] nodes = new Node[4];
        nodes[0] = new Node("a", 7);
        nodes[1] = new Node("b", 5);
        nodes[2] = new Node("c", 2);
        nodes[3] = new Node("d", 4);

        System.out.println("origin: " + Arrays.asList(nodes));

        HuffmanTree huffmanTree = new HuffmanTree();
        Node[] result = huffmanTree.buildTree(nodes);
        System.out.println("build tree: " + Arrays.asList(result));

        Node[] nodes1 = new Node[4];
        nodes1[0] = new Node("a", 7);
        nodes1[1] = new Node("b", 5);
        nodes1[2] = new Node("c", 2);
        nodes1[3] = new Node("d", 4);

        HuffmanTree huffmanTree1 = new HuffmanTree();
        Code[] codes = huffmanTree1.encode(nodes1);
        System.out.println("encode: " + Arrays.asList(codes));

        Node[] nodes2 = new Node[4];
        nodes2[0] = new Node("a", 7);
        nodes2[1] = new Node("b", 5);
        nodes2[2] = new Node("c", 2);
        nodes2[3] = new Node("d", 4);

        HuffmanTree huffmanTree2 = new HuffmanTree();
        System.out.println("decode: " + huffmanTree2.decode(nodes2, "010110111"));
    }

    class QuickSort {
        public void quickSort(Node[] array) {
            quickSort(array, 0, array.length - 1);
        }

        private void quickSort(Node[] array, int left, int right) {
            if (left + 10 <= right) {
                // 选择一个基准值，left right center由小到大排序后取中间值
                int pivot = median3(array, left, right);
                System.out.println("pivot: " + Arrays.asList(array));
                System.out.println("pivot: " + pivot);

                int i = left;
                int j = right - 1;

                for (; ; ) {
                    while (array[++i].getWeight() < pivot) {
                    }

                    while (array[--j].getWeight() > pivot) {
                    }

                    if (i < j) {
                        swap(array, i, j);
                        System.out.println(Arrays.asList(array));
                    } else {
                        break;
                    }
                }

                swap(array, i, right - 1);
                System.out.println(Arrays.asList(array));
                quickSort(array, left, i - 1);
                quickSort(array, i + 1, right);
            } else {
                insertionSort(array, left, right);
            }
        }

        private void insertionSort(Node[] array, int left, int right) {
            int j;
            for (int i = left; i <= right; i++) {
                Node temp = array[i];
                for (j = i; j > 0 && temp.getWeight() < array[j - 1].getWeight(); j--) {
                    array[j] = array[j - 1];
                }
                array[j] = temp;
            }
        }

        private int median3(Node[] array, int left, int right) {
            int center = (left + right) / 2;
            if (array[left].getWeight() > array[center].getWeight()) {
                swap(array, left, center);
                System.out.println(Arrays.asList(array));
            }

            if (array[left].getWeight() > array[right].getWeight()) {
                swap(array, left, right);
                System.out.println(Arrays.asList(array));
            }

            if (array[center].getWeight() > array[right].getWeight()) {
                swap(array, center, right);
                System.out.println(Arrays.asList(array));
            }

            // 将center和right - 1交换下，left center right已经排序，center作为pivot，left + 1 和right -1 可以作为两端
            swap(array, center, right - 1);
            System.out.println(Arrays.asList(array));
            return array[right - 1].getWeight();
        }

        private void swap(Node[] array, int m, int n) {
            Node temp = array[m];
            array[m] = array[n];
            array[n] = temp;
        }
    }

}
