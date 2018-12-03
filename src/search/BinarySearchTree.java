package search;

import java.util.ArrayList;

/**
 * 二叉查找树:
 * 二叉树, 且每个节点的键都大于其左子树的任意节点的键,
 * 小于其右子树的任意节点的键
 * 插入时如果Key相同, 视为更新
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Key[] keys;
    private Value[] values;
    private int N;
    private Node head = null;

    class Node {
        Node left;
        Node right;
        Key key;
        Value value;
    }

    public void put(Key key, Value value) {
        if (key == null)
            throw new IllegalArgumentException("key = null");
        if (head == null) {
            Node newNode = new Node();
            newNode.key = key;
            newNode.value = value;
            newNode.left = null;
            newNode.right = null;
            head = newNode;
            return;
        }
        Node iterator = head;
        Node lastNode = head;
        Node temp = new Node();
        temp.key = key;
        temp.value = value;
        temp.left = null;
        temp.right = null;
        while (iterator != null) {
            lastNode = iterator;
            if (key.compareTo(iterator.key) == 0) {
                iterator.value = value;
                break;
            } else if (key.compareTo(iterator.key) > 0) {
                iterator = iterator.right;
            } else {
                iterator = iterator.left;
            }
        }
        if (iterator == null) {
            if (key.compareTo(lastNode.key) > 0)
                lastNode.right = temp;
            else
                lastNode.left = temp;
        }
    }

    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException("key = null");
        Node iterator = head;
        while (iterator != null) {
            if (key.compareTo(iterator.key) == 0)
                return iterator.value;
            else if (key.compareTo(iterator.key) > 0)
                iterator = iterator.right;
            else
                iterator = iterator.left;
        }
        return null;
    }

    public Key min() {
        Node iterator = head;
        while (iterator != null) {
            if (iterator.left == null)
                return iterator.key;
            iterator = iterator.left;
        }
        return null;
    }

    public Key max() {
        Node iterator = head;
        while (iterator != null) {
            if (iterator.right == null)
                return iterator.key;
            iterator = iterator.right;
        }
        return null;
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key low, Key high) {
        ArrayList<Key> nodes = new ArrayList<>();
        keys(head, nodes, low, high);
        return nodes;
    }

    private void keys(Node node, ArrayList<Key> nodes, Key low, Key high) {
        if (node == null || low == null || high == null)
            return;
        int cmpLow = low.compareTo(node.key);
        int cmpHigh = high.compareTo(node.key);
        if (cmpLow < 0)
            keys(node.left, nodes, low, high);
        if (cmpLow <= 0 && cmpHigh >= 0)
            nodes.add(node.key);
        if (cmpHigh > 0)
            keys(node.right, nodes, low, high);
    }

    public static void main(String[] args) {
        BinarySearchTree<String, Integer> binarySearchTree = new BinarySearchTree<>();
        for (int i = 0; i < 30; i++) {
            binarySearchTree.put("" + (int)((Math.random() * 20)), i);
        }

        Iterable<String> iterable = binarySearchTree.keys();
        iterable.forEach(System.out::println);
    }
}
