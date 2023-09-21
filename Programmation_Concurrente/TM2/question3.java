package Programmation_Concurrente.TM2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class question3 {
    private static int sharedValue = 0;
    private static boolean flag = false;

    private static Lock lock = new ReentrantLock();
    private static Condition consumerCondition = lock.newCondition();
    private static Condition producerCondition = lock.newCondition();
    private static int consumerCount = 0;

    public static class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (flag) {
                        producerCondition.await();
                    }
                    sharedValue++;
                    flag = true;
                    System.out.println("Producer produced: " + sharedValue);
                    consumerCondition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static class Consumer implements Runnable {
        private String name;

        public Consumer(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (true) {
                lock.lock();
                try {
                    while (!flag || consumerCount >= 2) {
                        if (!flag) {
                            producerCondition.signal();
                        }
                        consumerCondition.await();
                    }
                    consumerCount++;
                    System.out.println(name + " consumed: " + sharedValue);
                    consumerCount--;
                    flag = false;
                    producerCondition.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread producerThread = new Thread(new Producer());
        Thread consumerThread1 = new Thread(new Consumer("Consumer 1"));
        Thread consumerThread2 = new Thread(new Consumer("Consumer 2"));

        producerThread.start();
        consumerThread1.start();
        consumerThread2.start();
    }
}