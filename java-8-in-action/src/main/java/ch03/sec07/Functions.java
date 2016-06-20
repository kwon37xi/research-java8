package ch03.sec07;

import java.util.function.Function;

public class Functions {
    public static void main(String[] args) {
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPipleline = addHeader.andThen(Letter::checkSpelling)
            .andThen(Letter::addFooter);

        final String apply = transformationPipleline.apply("hello labda!");
        System.out.println(apply);

        Function<Integer, Integer> f = x -> x + 1;
        Function<Integer, Integer> g = x -> x * 2;

        Function<Integer, Integer> h = f.andThen(g);
        System.out.println("f(2) and then g : " + h.apply(2));

        Function<Integer, Integer> j = f.compose(g);
        System.out.println("f(2) compose g : " + j.apply(2));

    }
}
