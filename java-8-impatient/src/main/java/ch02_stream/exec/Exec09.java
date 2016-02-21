package ch02_stream.exec;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Exec09 {
    /*
    Stream<ArrayList<T>>를 하나의 ArrayList<T>로 만든다.
     */
    public static void main(String[] args) {
        final Optional<ArrayList<Integer>> reduced1 = createArrayListStream()
            .reduce((integers, integers2) -> {
                integers.addAll(integers2);
                return integers;
            });

        reduced1.ifPresent(integers -> {
            System.out.println("reduced 1 : " + reduced1.get());
        });

        final List<Integer> reduced2 = createArrayListStream()
            .reduce(new ArrayList<>(), (integers, integers2) -> {
                integers.addAll(integers2);
                return integers;
            });

        System.out.println("reduced 2 : " + reduced2);

        // 세번째 방식은 무의미하다. 그래서 ArrayList의 length를 구하는 것으로 변경해봄.
        final Integer totalSize = createArrayListStream()
            .parallel()
            .reduce(0, (total, list) -> {
                    System.out.println("MidTotal : " + total + ", " + list);
                    final int midTotal = total + list.size();
                    return midTotal;
                }, (total1, total2) -> {
                    // parallel stream 일 때만 이 세번째 인자가 호출된다.
                    // sequential stream에서는 이 세번째 인자 람다는 호출되지 않고 호출의 의미도 없다.
                    System.out.println("Final Total1 : " + total1 + ", Total2 : " + total2);
                    return total1 * total2;
                }
            );
        System.out.println("reduce 3 : " + totalSize);
    }

    public static Stream<ArrayList<Integer>> createArrayListStream() {
        return Stream.of(new ArrayList(Arrays.asList(1, 2, 3, 4, 5)), new ArrayList(Arrays.asList(6, 7, 8, 9, 10)), new ArrayList(Arrays.asList(11, 12, 13, 14, 15)));
    }
}
