package ch12.sec06;

public class ThreadA extends Thread {
    public static boolean work;
    public static boolean stop;

    @Override
    public void run() {
        while (!stop) {
            if (work) {
                System.out.println("ThreadA 작업 내용");
            } else {
                Thread.yield();
            }
        }
        System.out.println("ThreadA 종료");
    }
}
