package ch12.sec01;

import java.util.Date;

public class CalcThread extends Thread {
    public CalcThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 500000000; i++) {
            new Date(); // dummy job
        }
        System.out.println(getName());
    }
}
