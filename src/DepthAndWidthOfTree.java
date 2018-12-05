import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 求二叉树的深度
 * https://www.cnblogs.com/xudong-bupt/p/4036190.html
 */
public class DepthAndWidthOfTree<Value> {

    class Node {
        Node left;
        Node right;
        Value value;
    }

    public int getTreeDepth(Node head) {
        if (head == null)
            return 0;
        int right = getTreeDepth(head.right);
        int left = getTreeDepth(head.left);
        return 1 + Math.max(right, left);
    }

    public int getMaxWidth(Node root) {
        if (root == null)
            return 0;

        Queue<Node> queue = new ArrayDeque<>();
        int maxWidth = 1;
        queue.add(root);

        while (true) {
            int len = queue.size();
            if (len == 0)
                break;
            while (len > 0) {
                Node t = queue.poll();
                len--;
                if (t.left != null)
                    queue.add(t.left);
                if (t.right != null)
                    queue.add(t.right);
            }
            maxWidth = Math.max(maxWidth, queue.size());
        }
        return maxWidth;
    }
}
