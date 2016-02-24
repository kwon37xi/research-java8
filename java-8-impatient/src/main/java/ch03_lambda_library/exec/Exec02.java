package ch03_lambda_library.exec;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class Exec02 {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 4; i++) {
            executorService.execute(() -> {
                for (int j = 0; j < 100; j++) {
                    withLock(reentrantLock, () -> {
                        System.out.println(Thread.currentThread().getName() + " - now " + sdf.format(new Date()));
                    });
                    try {
                        Thread.sleep((long) (Math.random() * 100));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

    }

    public static void withLock(ReentrantLock reentrantLock, Runnable runnable) {
        reentrantLock.lock();

        try {
            runnable.run();
        } finally {
            reentrantLock.unlock();
        }
    }
}
