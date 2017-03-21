package lombokpractice.value;

import lombok.*;
import lombok.experimental.NonFinal;
import lombok.experimental.Wither;

/**
 * https://projectlombok.org/features/Value.html
 *
 * {@code @Value}는 {@code @Data}의 불변객체 버전으로 모든 필드가 기본적으로 private final 로 만들어진다.
 * setter 는 생성되지 않는다.
 * 클래스도 final이 된다. 불변성은 상속될 수 없기 때문이다.
 * toString(), equals(), hashCode()도 생성된다.
 * 각 필드는 getter를 가진다.
 * 생성자는 모든 인자를 받는다.(초기화된 final 필드는 예외이다).
 * 즉, {@code final @ToString @EqualsAndHashCode @AllArgsConstructor @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE) @Getter)}와 같다.
 * 생성 대상 메소드가 이미 존재하면 경고 없이 안 생성한다.
 * 어떠한 명시적 생성자가 하나라도 존재하면 생성자도 만들지 않는다.
 * 명시적으로 모든 인자를 받는 생성자를 원하면 {@code @AllArgsConstructor}를 만들라.
 *
 * 이미 존재하는 메소드나 생성자에 {@code @lombok.experimental.Tolerate}를 지정하면 없는셈 친다.
 *
 * 필드에 {@code @NonFinal} 혹은 {@code PackagePrivate} 애노테이션으로 final-by-default, private-by-default 행위를 변경할 수 있다.
 *
 * configurations
 *
 * lombok.value.flagUsage = [warning | error] (default: not set)
 */
@Value
public class ValueExample {
    String name;
    @Wither(AccessLevel.PACKAGE) @NonFinal int age;
    double score;
    protected String[] tags;

    @ToString(includeFieldNames = true)
    @Value(staticConstructor = "of")
    public static class Exercise<T> {
        String name;
        T value;
    }

    // @Value와 @Builder를 연결하면 생성자를 Package Private으로 만든다.
    @Value
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder
    public static class ExWithBuilder {
        String name;
        int age;
    }

    public static void main(String[] args) {
        ValueExample ve = new ValueExample("valueExample", 11, 20.5, new String[] {"a", "b"});
        System.out.println(ve);

//        Exercise<Integer> ex = new Exercise<>("ex", 123);
        Exercise<Integer> ex = Exercise.of("ex", 123);
        System.out.println(ex);

        ExWithBuilder ewb = ExWithBuilder.builder().name("hi").age(44).build();
        System.out.println(ewb);
    }
}
