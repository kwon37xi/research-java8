package ch05.sec04;

import ch04.Menu;

import java.util.Arrays;
import java.util.List;

public class ReducingExample {
    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

//        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        int sum = numbers.stream().reduce(0, Integer::sum);
        System.out.println("Sum : " + sum);

        int product = numbers.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Product : " + product);


        numbers.stream().reduce(Integer::max)
            .ifPresent(i -> System.out.println("max : " + i));
        numbers.stream().reduce(Integer::min)
            .ifPresent(i -> System.out.println("min : " + i));

        // Quiz 5-3
        Menu.menus().stream()
            .map(m -> 1)
            .reduce(Integer::sum)
            .ifPresent(c -> System.out.println("Dish count : " + c));

    }
}
