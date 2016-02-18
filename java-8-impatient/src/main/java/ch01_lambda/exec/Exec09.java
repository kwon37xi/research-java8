package ch01_lambda.exec;

import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class Exec09 {
    public interface Collection2<T> extends Collection<T> {
        default void forEachIf(Consumer<T> action, Predicate<T> filter) {
            forEach((t) -> {
                if (filter.test(t)) {
                    action.accept(t);
                }
            });
        }
    }

    static class ArrayList2<T> extends ArrayList<T> implements Collection2<T> {

    }

    public static void main(String[] args) {
        ArrayList2<String> strings = new ArrayList2<>();
        strings.add("hello");
        strings.add("world");

        strings.forEachIf((str) -> System.out.println(str), (str) -> str.contains("d"));
    }
}
