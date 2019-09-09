package com.thanos.multiplethread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author Shi Qiang Yu
 * @date 9/9/2019 4:22 PM
 */
public class Main4 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newCachedThreadPool();

        LinkedBlockingDeque<Toast> toastQueue = new LinkedBlockingDeque<>();
        LinkedBlockingDeque<Toast> butteredQueue = new LinkedBlockingDeque<>();
        LinkedBlockingDeque<Toast> jammedQueue = new LinkedBlockingDeque<>();

        executorService.execute(new Toaster(toastQueue));
        executorService.execute(new Butterer(toastQueue, butteredQueue));
        executorService.execute(new Jammed(butteredQueue, jammedQueue));
        executorService.execute(new Eater(jammedQueue));

    }
}

class Toast {

    private Status status = Status.DRY;
    private final int id;

    Toast(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Status getStatus() {
        return status;
    }

    public void addButtered() {
        status = Status.BUTTERED;
    }

    public void addJammed() {
        status = Status.JAMMED;
    }

    @Override
    public String toString() {
        return "Toast " + id + " : " + status;
    }

    /**
     * 枚举类型
     */
    public enum Status {
        DRY, BUTTERED, JAMMED
    }
}

/**
 * 制作吐司
 */
class Toaster implements Runnable {
    private LinkedBlockingDeque<Toast> toastQueue;
    private int count;

    Toaster(LinkedBlockingDeque toastQueue) {
        this.toastQueue = toastQueue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                Toast toast = new Toast(count++);
                System.out.println(toast);
                toastQueue.put(toast);
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 添加黄油
 */
class Butterer implements Runnable {
    private LinkedBlockingDeque<Toast> toastQueue;
    private LinkedBlockingDeque<Toast> butteredQueue;

    Butterer(LinkedBlockingDeque toastQueue, LinkedBlockingDeque butteredQueue) {
        this.toastQueue = toastQueue;
        this.butteredQueue = butteredQueue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                Toast toast = toastQueue.take();
                toast.addButtered();
                System.out.println("添加黄油, " + toast);
                butteredQueue.put(toast);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 添加果酱
 */
class Jammed implements Runnable {
    private LinkedBlockingDeque<Toast> butteredQueue;
    private LinkedBlockingDeque<Toast> jammedQueue;

    Jammed(LinkedBlockingDeque butteredQueue, LinkedBlockingDeque jammedQueue) {
        this.butteredQueue = butteredQueue;
        this.jammedQueue = jammedQueue;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                Toast toast = butteredQueue.take();
                toast.addJammed();
                System.out.println("添加果酱, " + toast);
                jammedQueue.put(toast);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 消费吐司
 */
class Eater implements Runnable {
    private LinkedBlockingDeque<Toast> jammedQueue;

    Eater(LinkedBlockingDeque jammedQueue) {
        this.jammedQueue = jammedQueue;
    }

    private int counter;

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                Toast toast = jammedQueue.take();
                if (toast.getStatus() != Toast.Status.JAMMED) {
                    System.out.println("=====ERROR=====");
                } else {
                    System.out.println("消费了一个吐司");
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
