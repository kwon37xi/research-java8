package ch12.sec05;

import java.util.Date;

public class TargetThread extends Thread {
    @Override
    public void run() {
        for (long i = 0; i < 100000000; i++) {
            new Date(); // dummy
        }

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (long i = 0; i < 100000000; i++) {
            new Date(); // dummy
        }

    }

}
