package ch12.sec06;

public class JoinExample {
    public static void main(String[] args) {
        SumThread sumThread = new SumThread();
        sumThread.start();

        try {
            sumThread.join(); // 현재 쓰레드가 sumThread가 종료될 때까지 기다리게 한다.
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.println("1~100 합 : " + sumThread.getSum());
    }
}
