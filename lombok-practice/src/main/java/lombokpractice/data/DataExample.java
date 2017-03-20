package lombokpractice.data;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;

/**
 * {@code @Data} - {@code @ToString, @EqualsAndHashCode, @Getter / @Setter and @RequiredArgsConstructor}에 대한 shortcut.
 * 기본적인 설정만 가지고 있으므로 상세 설정을 하고자 한다면 각 어노테이션을 직접 선언해주면 {@code @Data}는 알아서
 * 이미 선언된 어노테이션을 빼고 적용한다.
 *
 * 모든 생성된 getter/setter는 public 이다. 변경하고자 한다면 클래스나 필드에 명시적으로 {@code @Setter, @Getter}를 붙인다.
 * {@code AccessLevel.NONE}으로 {@code @Getter/@Setter}를 선언하면 getter/setter를 생성하지 않는다.
 *
 * {@code transient} 필드는 hashCode/equals 에서 사용하지 않는다. 모든 static 필드도 무시된다.
 *
 * 생성 대상이 되는 메소드가 이미 동일한 파라미터 갯수를 가지고 존재한다면 아무런 오류 없이 생성안하고 넘어간다.
 * 어떠한 명시적 생성자라도 존재한다면 생성자를 자동으로 만들지 않는다. toString, equals, 모든 getter/setter 마찬가지 이다.
 *
 * {@code @lombok.experimental.Tolerate}를 메소드에 지정하면 lombok은 해당 메소드를 없는 셈 치고 코드를 생성한다.
 *
 * {@code staticConstructor="name"} 으로 private 생성자를 만들고, static method로 객체를 생성하게 할 수 있다.
 *
 * configurations
 * lombok.data.flagUsage = [warning | error] (default: not set)
 */
@Data
public class DataExample {
    private final String name;
    @Setter(AccessLevel.PACKAGE) private int age; // setAge()가 package level로 생성됨.
    private double score;
    private String[] tags;

    @ToString(includeFieldNames = true)
    @Data(staticConstructor = "of")
    public static class Exercise<T> {
        private final String name;
        private final T value;
    }
}
