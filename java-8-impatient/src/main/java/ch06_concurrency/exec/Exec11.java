package ch06_concurrency.exec;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Predicate until 이 true를 리턴하는 값을 생산할 때까지 비동기로 액션을 반복하는 repeat 메소드를 작성한다.
 * until 자체도 비동기로 실행해야 한다.
 * 콘솔에서 {@link java.net.PasswordAuthentication}을 읽는 함수와 1초 동안 sleep했다가 비밀번호가 "secret"인지
 * 검사하는 방법으로 테스트 한다. 재귀호출로 작성한다.
 */
public class Exec11 {
    public static void main(String[] args) {
        repeate(() -> (int) Math.random() * 10, integer -> integer.equals(5));
    }

    public static <T>CompletableFuture<T> repeate(Supplier<T> action, Predicate<T> until) {
        return CompletableFuture.supplyAsync(action)
            .handle((BiFunction<T, Throwable, T>) (value, throwable) -> {
                System.out.println("Error! " + throwable.getMessage());
                return null;
            })
            .thenApplyAsync(value -> {
                if (until.test(value)) {
                    System.out.println("Congratulations!");
                    return;
                }

                repeate(action, until);
            });
    }
}
