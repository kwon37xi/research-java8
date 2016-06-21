package ch04;


import java.util.Arrays;
import java.util.List;

import static ch04.Dish.Type.MEAT;
import static ch04.Dish.Type.FISH;
import static ch04.Dish.Type.OTHER;

public class Menu {
    public List<Dish> menus() {
        return Arrays.asList(
            new Dish("pork", false, 800, MEAT),
            new Dish("beef", false, 700, MEAT),
            new Dish("chicken", false, 400, MEAT),
            new Dish("french fries", true, 530, OTHER),
            new Dish("rice", true, 350, OTHER),
            new Dish("season fruit", true, 120, OTHER),
            new Dish("pizza", true, 550, OTHER),
            new Dish("prawns", false, 300, FISH),
            new Dish("salmon", false, 450, FISH)
        );
    }
}
