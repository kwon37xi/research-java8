package lombokpractice.getter_lazy;

import lombok.Getter;

/**
 * https://projectlombok.org/features/GetterLazy
 *
 * 특정필드에 {@code @Getter(lazy=true)}를 지정하면 해동 필드의 초기화 코드가 최초로 getter가 호출될 때 단 한번만
 * 실행되고 그 뒤로는 캐시된다.
 *
 * lombok이 알아서 lock을 처리하기 때문에 thread-safe하지 않은 초기화 코드도 상관없다.
 * 필드에 절대 직접 접근해서는 안된다. 해당 필드는 AtomicReference로 감싸진다. AtomicReference를 직접 다루지 말고 항상
 * getter를 사용하라. lombok은 {@code @ToString(doNotUseGetters=true)}로 지정하더라도 getter를 강제로 사용한다.
 *
 * configurations
 * - lombok.getter.lazy.flagUsage = [warning | error] (default: not set)
 */
public class GetterLazyExample {
    @Getter(lazy = true)
    private final double[] cached = expensive();

    private double[] expensive() {
        System.out.println("expensive called.");
        double[] result = new double[1000000];
        for (int i = 0; i < result.length; i++) {
            result[i] = Math.asin(i);
        }
        return result;
    }

    public static void main(String[] args) {
        final GetterLazyExample example = new GetterLazyExample(); // expensive 아직 호출안됨.
        System.out.println("expensive 아직 호출 안됨.");

        example.getCached();
        System.out.println("expensive 호출 한 번 되었음");

        example.getCached();
        System.out.println("두번째 getCached 호출 되었으나 expensive호출 안됨.");
    }
}
