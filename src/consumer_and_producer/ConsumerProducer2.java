package consumer_and_producer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 生产者和消费者
 * https://juejin.im/entry/596343686fb9a06bbd6f888c
 * 使用ReentrantLock与条件变量实现
 */
public class ConsumerProducer2 {
    private static final int FULL = 10;
    private int count = 0;
    private Lock lock = new ReentrantLock();
    private final Condition consume = lock.newCondition();
    private final Condition produce = lock.newCondition();

    public static void main(String[] args) {
        ConsumerProducer2 consumerProducer = new ConsumerProducer2();
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
                    Thread.sleep(500);
                    lock.lock();
                    while (count <= 0)
                        consume.await(); // 注意信号量使用的是await()和signalAll(), 不要使用wait()和notifyAll()
                    count--;
                    System.out.println(Thread.currentThread().getName()+" consume ---> " + count);
                    produce.signalAll();
                } catch (InterruptedException e) {
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Producer implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(500);
                    lock.lock();
                    while (count >= FULL)
                        produce.await();
                    count++;
                    System.out.println(Thread.currentThread().getName()+" produce ---> " + count);
                    consume.signalAll();
                } catch (InterruptedException e) {
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}
