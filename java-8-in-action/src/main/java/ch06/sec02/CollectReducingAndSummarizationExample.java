package ch06.sec02;

import ch04.Dish;

import static ch04.Menu.menus;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class CollectReducingAndSummarizationExample {
    public static void main(String[] args) {
//        long howManyDishes = Menu.menus().stream().collect(Collectors.counting());
        long howManyDishes = menus().stream().count();
        System.out.println("How many dishes : " + howManyDishes);

        menus().stream()
            .collect(maxBy(comparingInt(Dish::getCalories)))
            .ifPresent(max -> System.out.println("Max : " + max));

        System.out.println("total calories : " + menus().stream().collect(summingInt(Dish::getCalories)));
        System.out.println("average calories : " + menus().stream().collect(averagingInt(Dish::getCalories)));

        System.out.println("Summarizing calories : " + menus().stream().collect(summarizingInt(Dish::getCalories)));

        System.out.println("All dish names : " + menus().stream().map(Dish::getName).collect(joining(", ")));

        System.out.println("total calories with reduce : " + menus().stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j)));

        menus().stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2))
            .ifPresent(d -> System.out.println("Max calorie dish : " + d.getName()));

        System.out.println("total calories with stream reduce : " + menus().stream()
            .map(Dish::getCalories)
            .reduce(Integer::sum)
            .get()
        );

        System.out.println("total calories with IntStream : " + menus().stream()
            .mapToInt(Dish::getCalories)
            .sum()
        );

        // Quiz 6-1
        String shortMenu = menus().stream().map(Dish::getName).collect(joining());
        System.out.println("short menu : " + shortMenu);

        System.out.println("short menu 1 : " + menus().stream().map(Dish::getName).collect(reducing((s1, s2) -> s1 + s2)).get());
        System.out.println("short menu 3 : " + menus().stream().collect(reducing("", Dish::getName, (s1, s2) -> s1 + s2)));
    }
}
