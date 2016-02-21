package ch02_stream.exec;

import java.util.stream.Stream;

/*
 * Stream<Double>의 평균을 계산하라.
 */
public class Exec10 {
    public static void main(String[] args) {
        // 제일 쉬운 해결책
        System.out.println("Average : " + createDoubleStream().mapToDouble(Double::doubleValue).average().getAsDouble());

        final int[] count = new int[]{0};

        final Double total = createDoubleStream().reduce(0.0, (d1, d2) -> {
            count[0]++;
            return d1 + d2;
        });

        System.out.println("Average with reduce total : " + total + ", count : " + count[0] + ", average : " + total / count[0]);

        // reduce의 맨 앞에 initial 값이 없을 경우에는 count가 하나가 빠져서 제대로된 평균이 안나온다.
        // 그 경우에는 count를 1부터 시작해야 한다.
        final int[] count2 = new int[]{1}; // 1 부터시작.
        final Double total2 = createDoubleStream().reduce((d1, d2) -> {
            count2[0]++;
            return d1 + d2;
        }).orElse(0.0);

        System.out.println("Average with reduce2 total : " + total2 + ", count : " + count2[0] + ", average : " + total2 / count2[0]);

        /*
        reduce로 합계만 계산하고 stream.count() 로 나눌수는 없다. stream이 이미 닫혀버렸기 때문이다.
         */
        Stream<Double> stream3 = createDoubleStream();
        final Double total3 = stream3.reduce((d1, d2) -> d1 + d2).orElse(0.0);
        try {
            System.out.println("Average with redue & count : " + total3 / stream3.count());
        } catch (IllegalStateException ex) {
            System.out.println("IllegalStateException 이 발생한다. - " + ex.getMessage());
        }
    }

    private static Stream<Double> createDoubleStream() {
        return Stream.of(12.3, 34.7, 78.234, 64.23);
    }

}
