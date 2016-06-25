package ch05.sec03;

import ch04.Dish;
import ch04.Menu;

import java.util.Arrays;
import java.util.List;

public class SearchMatchingExample {
    public static void main(String[] args) {
        if (Menu.menus().stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

//        boolean isHealthy = Menu.menus().stream().allMatch(d -> d.getCalories() < 1000);
        boolean isHealthy = Menu.menus().stream().noneMatch(d -> d.getCalories() >= 1000);
        System.out.println("Healthy : " + isHealthy);

        Menu.menus().stream()
            .filter(Dish::isVegetarian)
            .findAny()
            .ifPresent(d -> System.out.println("Any dish : " + d.getName()));

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        someNumbers.stream()
            .map(x -> x * x)
            .filter(x -> x % 3 == 0)
            .findFirst()
            .ifPresent(n -> System.out.println("First % 3 number : " + n));

    }
}
