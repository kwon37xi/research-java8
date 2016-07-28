package ch14.sec03;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class LazyList<T> implements MyList<T> {
    private final T head;
    private final Supplier<MyList<T>> tail;

    public LazyList(T head, Supplier<MyList<T>> tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public T head() {
        return head;
    }

    @Override
    public MyList<T> tail() {
        return tail.get();
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public MyList<T> filter(Predicate<T> p) {
        return isEmpty() ?
            this :
            p.test(head) ?
                new LazyList<T>(head(), () -> tail().filter(p)) :
                tail().filter(p);
    }

	/**
     * n 부터 시작하는 무한한 LazyList
     */
    public static LazyList<Integer> from(int n) {
        return new LazyList<>(n, () -> from(n + 1));
    }
}
