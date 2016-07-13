package ch09.sec04;

/**
 *
 */
public class E extends D implements B, A {
    public static void main(String[] args) {
        new E().hello(); // B
    }
}
