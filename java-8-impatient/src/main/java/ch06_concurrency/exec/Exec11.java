package ch06_concurrency.exec;

import java.net.PasswordAuthentication;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
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
        repeate(() -> {
            // IntelliJ console에서는 실행안됨. 별도의 터미널에서 실행할것.
            System.console().printf("Username : ");
            final String username = System.console().readLine();
            System.console().printf("Password : ");
            final char[] password = System.console().readPassword();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return new PasswordAuthentication(username, password);

        }, pa -> Arrays.equals(pa.getPassword(), "secret".toCharArray()))
        .thenAccept(pa -> System.out.println("Username " + pa.getUserName() + " passed."));

        ForkJoinPool.commonPool().awaitQuiescence(10, TimeUnit.MINUTES);
    }

    // http://www.nurkiewicz.com/2013/05/java-8-definitive-guide-to.html
    // Speaking of get(), there is also CompletableFuture.join() method with some subtle changes in error handling.
    // But in general they are the same.
    public static <T> CompletableFuture<T> repeate(Supplier<T> action, Predicate<T> until) {
        return CompletableFuture.supplyAsync(action)
            .thenApplyAsync(value -> {
                System.out.println("current value : " + value);
                if (until.test(value)) {
                    return value;
                } else {
                    return repeate(action, until).join();
                }
            });
    }
}
