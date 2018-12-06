package tree;

import java.util.LinkedList;

/**
 * 二叉树相关题目
 * https://blog.csdn.net/luckyxiaoqiang/article/details/7518888
 */
public class BinaryTree<Value> {
    class Node {
        Node left;
        Node right;
        Value value;
    }

    /**
     * 求二叉树的节点个数
     */
    public int getNodeCount(Node head) {
        if (head == null)
            return 0;
        return getNodeCount(head.left) + getNodeCount(head.right) + 1;
    }

    /**
     * 求二叉树深度
     */
    public int getDepth(Node head) {
        if (head == null)
            return 0;
        int leftDepth = getDepth(head.left) + 1;
        int rightDepth = getDepth(head.right) + 1;
        return Math.max(leftDepth, rightDepth);
    }

    /**
     * 前序遍历
     */
    public void preOrderTraverse(Node head) {
        if (head == null)
            return;
        visitNode(head);
        preOrderTraverse(head.left);
        preOrderTraverse(head.right);
    }

    /**
     * 中序遍历
     */
    public void inOrderTraverse(Node head) {
        if (head == null)
            return;
        inOrderTraverse(head.left);
        visitNode(head);
        inOrderTraverse(head.right);
    }

    /**
     * 后序遍历
     */
    public void postOrderTraverse(Node head) {
        if (head == null)
            return;
        postOrderTraverse(head.left);
        postOrderTraverse(head.right);
        visitNode(head);
    }

    private void visitNode(Node node) {
        if (node == null)
            return;
        System.out.println(node.value);
    }

    /**
     * 分层遍历: 从上到下, 从左到右
     * 相当于广度优先
     * 使用队列
     */
    public void levelTraverse(Node head) {
        if (head == null)
            return;
        LinkedList<Node> nodes = new LinkedList<>();
        nodes.addLast(head);
        while (!nodes.isEmpty()) {
            Node visit = nodes.removeFirst();
            visitNode(visit);
            if (visit.left != null)
                nodes.addLast(visit.left);
            if (visit.right != null)
                nodes.addLast(visit.right);
        }
    }

    /**
     * 将二叉查找树变为有序的双向链表
     * 其实就是中序遍历
     */
    private Node listHead = null;
    private Node listTail = null;

    public Node convertToList(Node head) {
        visit(head);
        return listHead;
    }

    private void visit(Node node) {
        if (node == null)
            return;
        visit(node.left);
        convertToListCore(node);
        visit(node.right);
    }

    private void convertToListCore(Node node) {
        node.left = listTail;
        if (listTail != null)
            listTail.right = node;
        else
            listHead = node;
        listTail = node;
    }

    // TODO 未完

}
