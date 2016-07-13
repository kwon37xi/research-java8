package ch09.sec04;

/**
 *
 */
public interface B extends A {
    default void hello() {
        System.out.println("Hello from B");
    }
}
