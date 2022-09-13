package practise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BlockingQueueWaitNotify {

    List<Integer> list = new ArrayList<Integer>();
    Random random = new Random();
    Object lock = new Object();

    void producer() throws InterruptedException {
        while (true) {
            synchronized (lock) {
                if (list.size() == 10) {
                    lock.wait();
                }
                int number = random.nextInt(10);
                list.add(number);
                System.out.println("added element " + number + " list size : " + list.size());
                lock.notify();
            }
        }


    }

    void consumer() throws InterruptedException {
        while (true) {
        synchronized (lock) {
            if (list.size() == 0) {
                lock.wait();
            }
            if (random.nextInt(10) == 9) {
                int removed = list.remove(0);
                System.out.println("removed element " + removed + " list size : " + list.size());
                lock.notify();
            }
        }

        }


    }

    public static void main(String[] args) throws InterruptedException {
        BlockingQueueWaitNotify queueWaitNotify = new BlockingQueueWaitNotify();
        Thread producer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queueWaitNotify.producer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });



        Thread consumer = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queueWaitNotify.consumer();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        producer.start();
        consumer.start();
        producer.join();
        producer.join();

        System.out.println("Completed");


    }


}
