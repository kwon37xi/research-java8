package ch03_lambda_library;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * Lambda와 Generic
 *
 * 함수 타입에서는 항상 인자는 역변이고 리턴 값은 공변이다.
 * 일반적인 규칙은 인자 타입에는 super를 사용하고, 리턴 타입에는 extends를 사용하는 것이다.
 */
public class Lecture09 {
    public static void main(String[] args) throws IOException {
        String contents = new String(Files.readAllBytes(Paths.get("java-8-impatient/alice.txt")), StandardCharsets.UTF_8);
        Stream<String> words = Stream.of(contents.split("[\\P{L}]+")).skip(1);

        final String[] strings = words.toArray(String[]::new);// IntFunction<T[]> 생성자에 int 로 배열 크기가 전달된다.
        System.out.println(Arrays.toString(strings));

    }

    /* first가 생산한 것을 second가 사용하고 비동기로 실행 */
    public static <T> void doInOrderAdync(Supplier<? extends T> first, Consumer<? super T> second, Consumer<? super Throwable> handler) {
        Thread t = new Thread() {
            public void run() {
                try {
                    T result = first.get();
                    second.accept(result);
                } catch (Throwable t) {
                    handler.accept(t);
                }
            }
        };
        t.start();
    }

}
