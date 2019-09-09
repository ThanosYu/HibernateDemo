package com.thanos.multiplethread;

/**
 * @author Shi Qiang Yu
 * @date 9/3/2019 4:39 PM
 */
public class Main {

    public static void main(String[] args) {

        for (int i = 0; i < 2; i++) {
//            new Thread(new LiftOff()).start();
            new Thread(new LiftOff1()).start();
        }
    }
}

class LiftOff implements Runnable {
    private int countDown = 3;
    private static int taskCount = 0;
    private final int id = taskCount++;

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println("Thread id=" + id + " countDown=" + (countDown > 0 ? countDown : "LiftOff!"));
            //让掉当前线程 CPU 的时间片，使正在运行中的线程重新变成就绪状态，并重新竞争 CPU 的调度权
            Thread.yield();
        }
    }
}

class LiftOff1 extends Thread {
    private int countDown = 3;
    private static int taskCount = 0;
    private final int id = taskCount++;

    @Override
    public void run() {
        while (countDown-- > 0) {
            System.out.println("Thread id=" + id + " countDown=" + (countDown > 0 ? countDown : "LiftOff!"));
            Thread.yield();
        }
    }
}