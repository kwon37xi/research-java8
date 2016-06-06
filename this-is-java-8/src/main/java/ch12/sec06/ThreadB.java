package ch12.sec06;


public class ThreadB extends Thread  {
    public boolean work = true;
    public static boolean stop;

    @Override
    public void run() {
        while (!stop) {
            if (work) {
                System.out.println("ThreadB 작업 내용");
            } else {
                Thread.yield();
            }
        }

        System.out.println("ThreadB 종료");
    }
}
