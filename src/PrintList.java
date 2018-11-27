import java.util.LinkedList;

/**
 * 题目:
 * <<剑指Offer>> 面试题 6: 从尾到头打印链表
 */
public class PrintList {

    public static void printListRecursively(Node head) {
        if (head == null)
            return;
        if (head.next != null)
            printListRecursively(head.next);
        System.out.println(head.value);
    }

    public static void printListByStack(Node head) {
        LinkedList<Node> list = new LinkedList<>();
        Node temp = head;
        while(temp != null) {
            list.push(temp);
            temp = temp.next;
        }
        int count = list.size();
        for (int i = 0; i < count; i++)
            System.out.println(list.pop().value);
    }

    private static class Node {
        private String value;
        private Node next;

        public Node(String value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        Node head = new Node("Node head");
        Node temp = head;
        for (int i = 0; i < 10; i++) {
            Node node = new Node("Node " + i);
            temp.next = node;
            temp = node;
        }
        temp.next = null;
        printListRecursively(head);
        printListByStack(head);
    }
}
