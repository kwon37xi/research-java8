package ch05.sec01;

import ch04.Dish;
import ch04.Menu;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class FilteringSlicingExample {
    public static void main(String[] args) {
        // filter
        final List<Dish> vegetarians = Menu.menus().stream()
            .filter(Dish::isVegetarian)
            .collect(toList());
        System.out.println("Vegetarians " + vegetarians);

        // distinct
        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
            .filter(i -> i % 2 == 0)
            .distinct()
            .forEach(System.out::println);

        // limit
        final List<Dish> only3HighCalories = Menu.menus().stream()
            .filter(d -> d.getCalories() > 300)
            .limit(3)
            .collect(toList());
        System.out.println("Only 3 High Calories : " + only3HighCalories);

        // skip
        final List<Dish> skipped = Menu.menus().stream()
            .filter(d -> d.getCalories() > 300)
            .skip(2)
            .collect(toList());

        System.out.println("Skipped : " + skipped);

        // quiz 5-1
        final List<Dish> meat = Menu.menus().stream()
            .filter(d -> d.getType() == Dish.Type.MEAT)
            .limit(2)
            .collect(toList());
        System.out.println("Quiz 5-1 2 Meat : " + meat);

    }
}
