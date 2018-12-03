package consumer_and_producer;

import java.util.concurrent.Semaphore;

/**
 * 生产者和消费者
 * https://juejin.im/entry/596343686fb9a06bbd6f888c
 * Semaphore信号量实现
 */
public class ConsumerProducer4 {
    private Semaphore notFull = new Semaphore(10);
    private Semaphore notEmpty = new Semaphore(10);
    private Semaphore mutex = new Semaphore(1);
    private int curCount = 0;

    public static void main(String[] args) {
        ConsumerProducer4 consumerAndProducer4 = new ConsumerProducer4();
        new Thread(consumerAndProducer4.new Producer()).start();
        new Thread(consumerAndProducer4.new Consumer()).start();
        new Thread(consumerAndProducer4.new Producer()).start();
        new Thread(consumerAndProducer4.new Consumer()).start();
    }

    class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    notFull.acquire();
                    mutex.acquire();
                    curCount++;
                    System.out.println(Thread.currentThread().getName() + "--生产者生产, 当前总量: " + curCount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    notFull.release();
                    mutex.release();
                }
            }
        }
    }

    class Consumer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                try {
                    notEmpty.acquire();
                    mutex.acquire();
                    curCount--;
                    System.out.println(Thread.currentThread().getName() + "--消费者消费, 剩余余量: " + curCount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    notEmpty.release();
                    mutex.release();
                }

            }
        }
    }
}
