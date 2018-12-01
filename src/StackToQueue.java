import java.util.LinkedList;

/**
 * 题目:
 * <<剑指Offer>> 面试题 9: 用两个栈实现一个队列
 */
public class StackToQueue<T> {
    private LinkedList<T> tail = new LinkedList<>();
    private LinkedList<T> head = new LinkedList<>();

    public void add(T t) {
        tail.push(t);
    }

    public T get() {
        T t = remove();
        if (t == null)
            return null;
        head.push(t);
        return t;
    }

    public T remove() {
        if (head.size() <= 0 && tail.size() <= 0) {
            return null;
        } else if (head.size() <= 0) {
            int n = tail.size();
            for (int i = 0; i < n; i++) {
                T t = tail.pop();
                head.push(t);
            }
        }
        return head.pop();
    }

    public int size() {
        return head.size() + tail.size();
    }

    public static void main(String[] args) {
        StackToQueue<String> stackToQueue = new StackToQueue<>();
        for (int i = 0; i < 10; i++) {
            stackToQueue.add("string " + i);
        }
        int n = stackToQueue.size();
        for (int i = 0; i < n; i++) {
            System.out.println(stackToQueue.remove());
        }

    }
}
