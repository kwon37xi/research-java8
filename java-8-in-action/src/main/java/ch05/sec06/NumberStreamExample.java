package ch05.sec06;

import ch04.Dish;
import ch04.Menu;

import java.util.stream.IntStream;

public class NumberStreamExample {
    public static void main(String[] args) {
        System.out.println("Sum of calories : " + Menu.menus().stream()
            .mapToInt(Dish::getCalories)
            .sum());

        final int max = Menu.menus().stream()
            .mapToInt(Dish::getCalories)
            .max().orElse(0);
        System.out.println("max : " + max);

        final long evenCount = IntStream.rangeClosed(1, 100)
            .filter(n -> n % 2 == 0)
            .count();
        System.out.println("짝수 갯수 : " + evenCount);

        // 피타고라스 정리

        IntStream.rangeClosed(1, 100).boxed()
            .flatMap(a ->
                IntStream.rangeClosed(1, 100)
                    .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                    .filter(t -> t[2] % 1 == 0)
            )
            .limit(5)
            .forEach(t ->
                System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

    }
}
