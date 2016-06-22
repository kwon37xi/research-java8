package ch04.sec04;

import ch04.Menu;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class OperationsExample {
    public static void main(String[] args) {
        final List<String> names = Menu.menus().stream()
            .filter(d -> {
                System.out.println("filtering " + d.getName());
                return d.getCalories() > 300;
            })
            .map(d -> {
                System.out.println("mapping " + d.getName());
                return d.getName();
            })
            .limit(3)
            .collect(toList());
        System.out.println(names);
    }

    /*
    -- 결과
    filtering pork
    mapping pork
    filtering beef
    mapping beef
    filtering chicken
    mapping chicken
    [pork, beef, chicken]

    limit 에 의해 3개만 필터링이 작동하고 그 이후는 작동하지 않음 : short circuit
    filter/map은 다른 연산이지만 하나로 병합되었다 : loop fusion
     */
}
