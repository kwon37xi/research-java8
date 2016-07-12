package ch09.sec01;

public interface Sized {
    int size();
    default boolean isEmpty() {
        return size() == 0;
    }
}
