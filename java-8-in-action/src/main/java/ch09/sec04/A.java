package ch09.sec04;

/**
 *
 */
public interface A {
    default void hello() {
        System.out.println("Hello from A");
    }
}
