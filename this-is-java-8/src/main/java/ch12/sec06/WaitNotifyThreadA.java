package ch12.sec06;

public class WaitNotifyThreadA extends Thread {
    private WorkObject workObject;

    public WaitNotifyThreadA(WorkObject workObject) {
        this.workObject = workObject;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            workObject.methodA();
        }
    }
}
