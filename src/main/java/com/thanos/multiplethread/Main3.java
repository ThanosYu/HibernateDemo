package com.thanos.multiplethread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Shi Qiang Yu
 * @date 9/9/2019 2:23 PM
 */
public class Main3 {

    public static void main(String[] args) throws InterruptedException {
        Restaurant restaurant = new Restaurant();
        new Thread(new ProductorThread(restaurant)).start();
        Thread.sleep(20);
        new Thread(new ConsumerThread(restaurant)).start();
    }
}

class Restaurant {

    private Lock lock = new ReentrantLock();//锁
    private Condition condition1 = lock.newCondition();//条件1
    private Condition condition2 = lock.newCondition();//条件2

    private int count;//已做好的餐

    /**
     * 消费者方法
     */
    public void comsumer() {
        lock.lock();
        try {
            if (count == 0) {
                System.out.println(Thread.currentThread().getId() + "客户 想要一份快餐！");
                // signal 是发出信号的意思，调它 就是 唤醒读线程
                condition2.signalAll();
                System.out.println(Thread.currentThread().getId() + "客户 等待一份快餐！");
                // await() 是等待的意思，调它 就是 阻塞写线程
                condition1.await();
            }
            count--;
            System.out.println(Thread.currentThread().getId() + "客户 消费了一份快餐！");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 生产者方法
     */
    public void productor() {
        lock.lock();
        try {
            condition2.await();
            count++;//生产一份快餐
            System.out.println(Thread.currentThread().getId() + "厨师 制作了一份快餐！");
            condition1.signalAll();
            System.out.println(Thread.currentThread().getId() + "厨师 通知客户使用");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

/**
 * 消费者
 */
class ConsumerThread implements Runnable {

    private Restaurant restaurant;

    ConsumerThread(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        restaurant.comsumer();
    }
}

/**
 * 生产者
 */
class ProductorThread implements Runnable {

    private Restaurant restaurant;

    ProductorThread(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        restaurant.productor();
    }
}