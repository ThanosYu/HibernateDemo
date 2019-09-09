package com.thanos.multiplethread;

/**
 * @author Shi Qiang Yu
 * @date 9/3/2019 5:24 PM
 */
public class Main2 {

    public static void main(String[] args) {

        Thread thread = new Thread(new ExceptionThread());
        thread.setUncaughtExceptionHandler(new MyExceptionHandler());
        thread.start();
    }
}

/**
 * 任务类
 */
class ExceptionThread implements Runnable {

    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("ExceptionThread当前线程信息：" + t.toString());
        System.out.println("当前线程ExceptionThread的异常处理器： " + t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

/**
 * 线程异常处理器
 */
class MyExceptionHandler implements Thread.UncaughtExceptionHandler {

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("抛出的异常是：" + e);
    }
}
