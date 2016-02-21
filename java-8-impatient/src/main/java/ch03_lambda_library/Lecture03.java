package ch03_lambda_library;

import javafx.scene.paint.Color;

import java.util.function.Predicate;
import java.util.stream.Stream;

public class Lecture03 {
    public static void main(String[] args) {

        final Stream<Integer> integers = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        integers
            .filter(Predicate.isEqual(1).or(Predicate.isEqual(10)))
            .forEach(System.out::println);
    }

    @FunctionalInterface
    public interface ColorTransformer {
        Color apply(int x, int y, Color colorAtXY);
    }
}
