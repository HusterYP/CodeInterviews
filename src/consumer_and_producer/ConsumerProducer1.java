package consumer_and_producer;

/**
 * 生产者和消费者
 * https://juejin.im/entry/596343686fb9a06bbd6f888c
 * 使用wait()和notifyAll()实现
 * 注意同步和唤醒的时机
 */
public class ConsumerProducer1 {
    private static final int FULL = 10;
    private int count = 0;
    private Object lock = new Object();

    public static void main(String[] args) {
        ConsumerProducer1 consumerProducer = new ConsumerProducer1();
        new Thread(consumerProducer.new Consumer()).start();
        new Thread(consumerProducer.new Consumer()).start();
        new Thread(consumerProducer.new Producer()).start();
        new Thread(consumerProducer.new Producer()).start();
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    synchronized (lock) {
                        while (count <= 0) // 注意这里必须使用while循环来不断检查count的值, 使用if判断会出现负值
                            lock.wait();
                        count--;
                        System.out.println(Thread.currentThread().getName()+" consume ---> " + count);
                        lock.notifyAll();
                    }
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    synchronized (lock) {
                        while (count >= FULL)
                            lock.wait();
                        count++;
                        System.out.println(Thread.currentThread().getName()+" produce ---> " + count);
                        lock.notifyAll();
                    }
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                }
            }
        }
    }
}
