package ch02_stream.exec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/*
 * 문제가 무슨 소리인지 모르겠음. 번역이 잘못된 느낌.
 *
 * 단일 ArrayList를 스트림의 크기로 생성했다면, 여러 ArrayList를 병합하는 대신 단일 리스트 안에 스트림 결과들을 동시에 모을 수 있어야 한다.
 * 이는 떨어진 위치에서 평행 set 연산은 쓰레드에 안전하기 때문이다.
 * 이 작업을 어떻게 할 수 있나?
 *
 * -> 문제의 의도를 마음대로 해석해보면, Stream<ArrayList<T>> 가 가진 모든 ArrayList.size()의 총 합과 동일한 size의 ArrayList<T> all가 존재할 때
 * Streawm<ArrayList<T>>를 parallel 로 해당 값들을 모두 all에 넣어줄 수 있어야 한다.
 *
 * -> 결론은, 이 작업을 할 수는 있지만 무의미하다. 이 일을 하려면 stream의 모든 값을 인덱스에 맞춰 일렬로 펼친뒤(flatMap) List 혹은 Array로 만들어야한다.
 * 그 순간에 이미 병렬작업이 아닌 선형 작업이 된다.
 *
 * 억지로 한다면 AtomicInteger를 통해서는 할 수는 있다.
 *
 */
public class Exec11 {
    public static void main(String[] args) {

        final Stream<ArrayList<Integer>> stream = Stream.of(new ArrayList<>(Arrays.asList(1, 2, 3)), new ArrayList<>(Arrays.asList(4, 5)), new ArrayList<>(Arrays.asList(6, 7, 8, 9, 10)));

        ArrayList<Integer> all = new ArrayList<>(Arrays.asList(new Integer[10]));

        AtomicInteger indexer = new AtomicInteger();

        stream.parallel().forEach(integers -> integers.parallelStream().forEach(i -> all.set(indexer.getAndIncrement(), i)));

        System.out.println("전체 병합 결과 : " + all);
    }
}
