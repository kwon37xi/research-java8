package ch02_stream;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Lecture07_Optional {
    public static void main(String[] args) {
        List<Integer> results = new ArrayList<>();
        Optional<Integer> optionalValue = Optional.of(100);

        // null이 아닐때만 실행됨.
        optionalValue.ifPresent(results::add); // no return

        // null이 아닐때만 실행됨.
        final Optional<Boolean> added = optionalValue.map(results::add); // 함수 실행결과 리턴.

        Optional<String> optionalString = Optional.empty();
        final String result = optionalString.orElse("");
        String defaultDir = optionalString.orElseGet(() -> System.getProperty("user.dir")); // default 값을 구해서 리턴.
        System.out.println(defaultDir);

        try {
            optionalString.orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException ex) {
            System.out.println("NoSuchElementException thrown");
        }

        inverse(4.0).flatMap(Lecture07_Optional::squareRoot)
                .ifPresent(System.out::println);

        Optional.of(-4.0)
                .flatMap(Lecture07_Optional::inverse)
                .flatMap(Lecture07_Optional::squareRoot)
                .ifPresent(System.out::println); // result is null, nothing printed
    }

    public static Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    public static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}
