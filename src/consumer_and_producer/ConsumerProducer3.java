package consumer_and_producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 生产者和消费者
 * https://juejin.im/entry/596343686fb9a06bbd6f888c
 * 使用BlockingQueue实现
 */
public class ConsumerProducer3 {
    private int count = 0;
    private BlockingQueue<Integer> pool = new ArrayBlockingQueue<>(10);

    public static void main(String[] args) {
        ConsumerProducer3 consumerProducer = new ConsumerProducer3();
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
                    pool.take();
                    count--;
                    System.out.println(Thread.currentThread().getName()+" consume ---> " + count);
                } catch (InterruptedException e){
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
                    pool.put((int) (Math.random() * 100));
                    count++;
                    System.out.println(Thread.currentThread().getName()+" produce ---> " + count);
                } catch (InterruptedException e){
                }
            }
        }
    }
}
