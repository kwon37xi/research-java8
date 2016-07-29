package ch14.sec05;

import java.util.function.Function;

public class CombinatorExample {
    public static <A, B, C> Function<A, C> compose(Function<B, C> g, Function<A, B> f) {
        return x -> g.apply(f.apply(x));
    }

    public static <A> Function<A, A> repeat(int n, Function<A,A> f) {
        return n == 0 ? x -> x : compose(f, repeat(n-1, f));
    }
    public static void main(String[] args) {
        System.out.println("Repeat 3 : " + repeat(3, (Integer x) -> 2 * x).apply(5));
    }
}
