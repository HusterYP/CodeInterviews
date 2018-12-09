package tree;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * 二叉树相关题目
 * https://blog.csdn.net/luckyxiaoqiang/article/details/7518888
 * 总结:
 * 涉及到二叉树的题目, 通常可以使用递归解决, 如果不能用递归, 那么可以考虑结合栈, 队列等来辅助解决
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

    /**
     * 求二叉树第K层节点个数
     * 递归:
     * 即返回左子树第k-1层节点及右子树第k-1层节点
     */
    public int getNodeNumKthLevel(Node head, int k) {
        if (head == null || k <= 0)
            return 0;
        if (k == 1)
            return 1;
        return getNodeNumKthLevel(head.left, k - 1) + getNodeNumKthLevel(head.right, k - 1);
    }

    /**
     * 求二叉树中叶子节点的个数
     */
    public int getLeafNodeNum(Node head) {
        if (head == null)
            return 0;
        if (head.left == null && head.right == null)
            return 1;
        return getLeafNodeNum(head.left) + getLeafNodeNum(head.right);
    }

    /**
     * 判断两棵二叉树结构是否相同
     * 注意: 只要结构相同即可, 不需要值相同
     * 同时注意比较最近这几道题, 思路都一样
     */
    public boolean structureCmp(Node node1, Node node2) {
        if (node1 == null && node2 == null)
            return true;
        if (node1 == null || node2 == null)
            return false;
        return structureCmp(node1.left, node2.left) && structureCmp(node1.right, node2.right);
    }

    /**
     * 判断二叉树是不是平衡树
     * 其实就是求出树的最高高度和最低高度
     */
    public boolean isAVL(Node head) {
        if (head == null)
            return true;
        int maxDepth = getDepth(head, true);
        int minDepth = getDepth(head, false);
        return maxDepth - minDepth <= 1;
    }

    private int getDepth(Node node, boolean maxDepth) {
        if (node == null)
            return 0;
        int left = getDepth(node, maxDepth) + 1;
        int right = getDepth(node, maxDepth) + 1;
        return maxDepth ? Math.max(left, right) : Math.min(left, right);
    }

    /**
     * 求二叉树的镜像
     * 递归, 让左右子树分别镜像
     */
    public Node mirror(Node head) {
        if (head == null)
            return null;
        Node cmp = head.left;
        head.left = head.right;
        head.right = cmp;
        mirror(head.left);
        mirror(head.right);
        return head;
    }

    /**
     * 求二叉树中两个节点的最低公共祖先节点
     * 参考博客: https://blog.csdn.net/zjkC050818/article/details/72621487
     * 注意考虑情况
     * 一下将二叉树视为一普通树来做
     */
    public Node fatherNode(Node head, Node node1, Node node2) {
        if (node1 == null || node2 == null || head == null)
            return null;
        ArrayList<Node> path1 = new ArrayList<>();
        ArrayList<Node> path2 = new ArrayList<>();
        ArrayList<Node> tempList = new ArrayList<>();
        getNodePath(head, node1, path1, tempList);
        getNodePath(head, node2, path2, tempList);
        Node common = null;
        int count = Math.min(path1.size(), path2.size());
        for (int i = 0; i < count; i++) {
            if (path1.get(i) != path2.get(i))
                break;
            common = path1.get(i);
        }
        return common;
    }

    private void getNodePath(Node head, Node target, ArrayList<Node> path, ArrayList<Node> temp) {
        if (head == null || target == null || head == target)
            return;
        temp.add(head);
        if (head.left == target || head.right == target) {
            path.addAll(temp);
            return;
        }
        getNodePath(head.left, target, path, temp);
        getNodePath(head.right, target, path, temp);
    }
}
