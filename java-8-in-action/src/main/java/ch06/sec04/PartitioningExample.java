package ch06.sec04;

import ch04.Dish;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ch04.Menu.menus;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;

public class PartitioningExample {
    public static void main(String[] args) {
        Map<Boolean, List<Dish>> partitionedMenu =
            menus().stream().collect(partitioningBy(Dish::isVegetarian));
//            menus().stream().collect(groupingBy(Dish::isVegetarian));

        System.out.println("Vegetarian : " + partitionedMenu.get(Boolean.TRUE));

        final Map<Boolean, Map<Dish.Type, List<Dish>>> vegetarianDishesByType = menus().stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));
        System.out.println("vegetarian dishes by type : " + vegetarianDishesByType.get(Boolean.TRUE));

        final Map<Boolean, Dish> mostCaloricPartitionedByVegetarian =
            menus().stream().collect(partitioningBy(Dish::isVegetarian, collectingAndThen(maxBy(comparingInt(Dish::getCalories)), Optional::get)));
        System.out.println("most caloric vegetarian : " + mostCaloricPartitionedByVegetarian);

        // quiz 6-2
        System.out.println("채식과 500칼로리 넘는지 여부 기준 : "  +
            menus().stream().collect(partitioningBy(Dish::isVegetarian, partitioningBy(d -> d.getCalories() > 500))));

        // partitioning은 Boolean만을 기준으로 한다.
        // menus().stream().collect(partitioningBy(Dish::isVegetarian, partitioningBy(Dish::getType)));

        final Map<Boolean, Long> vegeCount = menus().stream().collect(partitioningBy(Dish::isVegetarian, counting()));
        System.out.println("채식주의 식품 갯수 : " + vegeCount);

    }
}
