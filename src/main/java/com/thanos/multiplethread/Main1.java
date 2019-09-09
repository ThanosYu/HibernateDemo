package com.thanos.multiplethread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author Shi Qiang Yu
 * @date 9/3/2019 4:55 PM
 */
public class Main1 {
    public static void main(String[] args) throws Exception {

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 50; i++) {
            Future future = executorService.submit(new TaskWithResult(i));
            Object result = future.get();
            System.out.println(result);
        }
    }
}

class TaskWithResult implements Callable {
    private int id;

    TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public Object call() {
        return "Thread id " + id;
    }
}