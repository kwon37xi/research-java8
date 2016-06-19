package ch03.sec04;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class PredicateExample {
    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<T>();
        for (T s: list) {
            if (p.test(s)) {
                results.add(s);
            }
        }
        return results;
    }

    public static void main(String[] args) {
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> strings = new ArrayList<>();
        strings.add("hello");
        strings.add("");
        strings.add("world");
        strings.add("안녕~");

        List<String> nonEmpty = filter(strings, nonEmptyStringPredicate);
        System.out.println("Results : " + nonEmpty);
    }
}
