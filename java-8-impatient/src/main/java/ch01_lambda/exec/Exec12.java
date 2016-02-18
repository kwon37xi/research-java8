package ch01_lambda.exec;

import java.util.Arrays;
import java.util.List;

/**
 * 과거 코드중 ArrayList 등을 상속한 클래스가 stream() 메소드를 직접 구현하면 문제가 발생할 수 있다.
 */
public class Exec12 {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("hello", "world");

        strings.stream().forEach((str) -> System.out.println(str));
    }
}
