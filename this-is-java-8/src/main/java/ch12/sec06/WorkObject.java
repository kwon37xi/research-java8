package ch12.sec06;

public class WorkObject {
    public synchronized void methodA() {
        System.out.println("WaitNotifyThreadA의 methodA() 작업 실행");

        notify();

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void methodB() {
        System.out.println("WaitNotifyThreadB의 methodB() 작업 실행");
        notify();

        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
