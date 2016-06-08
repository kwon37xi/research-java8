package ch12.sec07;

public class DaemonExample {
    public static void main(String[] args) {
        AutoSaveThread autoSaveThread = new AutoSaveThread();
        autoSaveThread.setDaemon(true); // 이를 호출하지 않으면 main thread 종료후에도 계속 애플리케이션이 실행된다.
        autoSaveThread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Main Thread 종료");
    }
}
