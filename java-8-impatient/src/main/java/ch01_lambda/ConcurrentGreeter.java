package ch01_lambda;

public class ConcurrentGreeter extends Greeter {
    public void greet() {
        Thread t = new Thread(super::greet); // 여기서 super 는 ConcurrentGreeter 객체의 super 즉 Greeter를 의미한다.
        t.start();

        // 위코드는,
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                ConcurrentGreeter.super.greet();
            }
        });
    }
}
