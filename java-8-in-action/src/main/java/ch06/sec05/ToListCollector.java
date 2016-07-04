package ch06.sec05;

import ch04.Dish;
import ch04.Menu;

import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 * T : 스트림의 항목 타입
 * 첫번째 ArrayList&lt;T&gt; : 누적자. 중간 데이터 저장소
 * 두번째 List&lt;T&gt; : 최종 결과
 * @param <T> 스트림 데이터
 */
public class ToListCollector<T> implements Collector<T, ArrayList<T>, List<T>> {
    // 비어있는 누적자 인스턴스
    @Override
    public Supplier<ArrayList<T>> supplier() {
        return ArrayList::new;
    }

    // 누적자에 다음 값을 누적시키는 역할
    @Override
    public BiConsumer<ArrayList<T>, T> accumulator() {
        return ArrayList::add;
    }

    // 스트림 탐색을 끝내고 누적된 데이터를 최종 결과로 변환하면서 누적 과정을 끝낼 때 호출할 함수
    @Override
    public Function<ArrayList<T>, List<T>> finisher() {
        return ts -> ts;
    }

    @Override
    public BinaryOperator<ArrayList<T>> combiner() {
        return (list1, list2) -> {
            list1.addAll(list2);
            return list1;
        };
    }

    @Override
    public Set<Characteristics> characteristics() {
        return Collections.unmodifiableSet(EnumSet.of(Characteristics.CONCURRENT, Characteristics.IDENTITY_FINISH));
    }

    public static void main(String[] args) {
//        final List<Dish> dishes = Menu.menus().stream().collect(new ToListCollector<>());
        final List<Dish> dishes = Menu.menus().stream().collect(
            ArrayList::new,
            List::add,
            List::addAll
        );
        System.out.println("Dishes with ToListCollector : " + dishes);


    }
}
