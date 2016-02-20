package ch02_stream.exec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 문제의 의도 파악이 안됨. "first와 second 스트림의 요소들을 교환하라"의 의미가 무엇인가?
 * 일반적으로 Python과 Scala의 zip 메소드의 의미를 따라서 두 스트림의 동일 position에 있는 값을 배열로 합쳐서 리턴하도록 한다.
 * 즉, Stream&lt;List&lt;T&gt;&gt; 를 리턴한다.
 */
public class Exec08 {
    public static void main(String[] args) {

        List<Integer> first = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        List<Integer> second = Arrays.asList(100, 200, 300, 400, 500);


        zip(first.stream(), second.stream()).forEach(zipped -> System.out.printf("(%d, %d)%n", zipped.get(0), zipped.get(1) ));
        System.out.println("===========");
        zip(second.stream(), first.stream()).forEach(zipped -> System.out.printf("(%d, %d)%n", zipped.get(0), zipped.get(1) ));

    }

    public static <T> Stream<List<T>> zip(Stream<T> firstStream, Stream<T> secondStream) {
        if (firstStream == null || secondStream == null) {
            return Stream.empty();
        }

        final Iterator<T> firstIterator = firstStream.iterator();
        final Iterator<T> secondIterator = secondStream.iterator();

        List<List<T>> zipped = new ArrayList<>();
        while (firstIterator.hasNext() && secondIterator.hasNext()) {
            zipped.add(Arrays.asList(firstIterator.next(), secondIterator.next()));
        }
        return zipped.stream();

    }
}
