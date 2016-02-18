package ch01_lambda.exec;

import java.util.Arrays;

/**
 * Arrays.sort의 비교자 코드는 sort 호출과 같은 쓰레드에서 실행된다.
 */
public class Exec01 {
    public static void main(String[] args) {
        String[] strings = {"hello", "hi"};

        System.out.println("main thread : " + Thread.currentThread().getName()); // main
        Arrays.sort(strings, (str1, str2) -> {
            System.out.println("Comparator thread : " + Thread.currentThread().getName()); // main
            return Integer.compare(str1.length(), str2.length());
        });

    }
}
