package lombokpractice.synchronizedlombok;

import lombok.SneakyThrows;
import lombok.Synchronized;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * https://projectlombok.org/features/Synchronized
 *
 * java의 {@code synchronized}는 메소드에 걸경우 {@code this} 객체에 락을 걸리만, {@code Synchronized}는
 * {@code $lock} 이라는 private 필드를 만들어서 락을 건다.
 * static 메소드의 경우 {@code $LOCK}이라는 static 필드에 락을 건다.
 *
 * - {@code $lock 혹은 $LOCK}을 직접 생성해도된다. 그 경우 자동 생성은 안된다.
 * - 원하는 lock 필드를 직접 명시해도 된다.
 *
 * {@code this} 객체에 락을 걸 경우 부작용이 발생할 수 있는데, 특히 전혀 알지못하는 어떤 코드에서
 * 특정 객체에 lock을 걸어버릴 경우에 문제 소지가 있다. 그래서 private 필드를 따로 만들어 락을 건다.
 *
 * configuration
 * - lombok.synchronized.flagUsage = [warning | error] (default: not set)
 */
public class SynchronizedExample {
    private final Object readLock = new Object();

    @SneakyThrows
    @Synchronized
    public static void hello() {
        System.out.println("World");
        TimeUnit.SECONDS.sleep(5);
    }

    @SneakyThrows
    @Synchronized
    public int answerToLife() {
        return 42;
    }

    @SneakyThrows
    @Synchronized("readLock")
    public void foo() {
        System.out.println(Thread.currentThread().getName() + ", " + LocalDateTime.now() + " : bar");
        TimeUnit.SECONDS.sleep(5);
    }

    public static void main(String[] args) throws InterruptedException {
        SynchronizedExample se = new SynchronizedExample();

        new Thread(() -> se.foo()).start();
        new Thread(() -> se.foo()).start(); // 5초 뒤에 bar 출력됨.

        System.out.println("The End...");

    }
}
