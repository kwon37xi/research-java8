package ch02;

public class Sec2_4_2_Runnable {
    public static void main(String[] args) {
        Thread t = new Thread(() -> System.out.println("Hello World"));
        t.start();
    }
}
