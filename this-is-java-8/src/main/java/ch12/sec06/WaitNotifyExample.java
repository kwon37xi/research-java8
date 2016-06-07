package ch12.sec06;

public class WaitNotifyExample {
    public static void main(String[] args) {
        WorkObject sharedObject = new WorkObject();

        WaitNotifyThreadA threadA = new WaitNotifyThreadA(sharedObject);
        WaitNotifyThreadB threadB = new WaitNotifyThreadB(sharedObject);

        threadA.start();
        threadB.start();
    }
}
