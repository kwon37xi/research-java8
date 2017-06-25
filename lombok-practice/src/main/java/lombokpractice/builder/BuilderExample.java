package lombokpractice.builder;

import lombok.Builder;
import lombok.Singular;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

/**
 * {@code @Builder} 객체 빌더를 만든다.
 *
 * https://projectlombok.org/features/Builder.html
 *
 * Class, 생성자, 혹은 메소드에 지정할 수 있다. Class나 생성자가 가장 일반적인 경우이다.
 *
 * 대상 Foo class 에 빌더를 지정하면
 * - inner static class FooBuilder를 만든다. {@code builder}라 부른다.
 * - builder에 대상의 각 파라미터에 대해 non-static, non-final 필드가 생긴다.
 * - builder에 package private 으로 기본 생성자가 생긴다.
 * - builder에 대상의 각 파라미터에 대해 setter 유사 메소드가 생긴다. 대상 파라미터와 동일한 이름, 동일한 타입을 가진다.
 *   그리고 builder 객체 그 자체를 리턴한다. 따라서 setter 메소드는 chaining이 가능하다.
 * - builder에 {@code build()} 메소드가 생기고 이를 호출하면 대상 클래스 객체를 리턴한다.
 * - builder에 합당한 수준의 {@code toString()} 이 생긴다.
 * - 대상을 가진 클래스에 static {@code builder()} 메소드가 생기고 이는 새로운 builder 객체를 생성한다.
 *
 * 위의 각 요소들은 이미 존재하면 경고없이 생성하지 않고 건너뛴다(파라미터 갯수는 상관 없고, 오직 이름만 본다).
 * 이는 builder 그 자체도 포함한다. 만약 builder 클래스가 존재한다면 lombok은 존재하는 builder 클래스에
 * 필드와 메소드를 넣으려고 든다. 물론 필드와 메소드가 이미 존재하지 않을 경우에만 한한다.
 *
 * builder 클래스에 다른 코드 생성 어노테이션을 두지 않는다({@code @EqualsAndHashCode} 같은).
 *
 * <code>@Singular</code> 메소드 : 컬렉션 파라미터/필드에 대한 단건 추가 메소드. 1개의 값만 받아서 기존 컬렉션에 추가한다.
 * 예를들어, <code>Person.builder().job("Mythbusters).job("unchained reaction").build(); 는 List&lt;String&gt; jobs에 요소를 추가한다.</code>
 *
 * 메소드 모드 : 생성자 대신 해당 객체를 리턴하는 static 메소드에 @Builder 추가.
 *
 * 클래스 모드 : class에 @Builder를 지정하면 생성자가 존재하지 않으면
 * <code>@AllArgsConstructor(access = AccessLevel.PACKAGE)</code>를 생성한다.
 *
 * <code>@Builder(toBuilder=true)</code> 이미 존재하는 객체의 상태값을 그대로 가지고서 새로운 Builder 객체를 만드는 메소드
 * <code>toBuilder</code>를 생성하게 함. <code>@Builder.obtainVia(method/field="")</code>는 toBuilder=true일 때 기존 인스턴스에서
 * 값을 가져오는 필드를 변경하고자 할 때 사용한다.
 *
 * Builder 클래스 이름은 기본적으로 생성자의 클래스타입 혹은 메소드의 리턴타입 + Builder 로 정해진다.
 * 메소드 리턴타입이 void 이면 VoidBuilder를 생성한다.
 *
 * builderClassName, buildMethodName, builderMethodName 등을 명시할 수 있다.
 *
 * <h2>@Builder.Default</h2>
 * 특정 필드 값을 지정할 일이 없을 경우 해당 필드의 기본값을 사용하게 한다. 필드를
 * 특정 값으로 초기화하게 했더라도 이 어노테이션을 지정하고 Builder를 사용해야만 초기값이 남아있게 된다. 그렇지
 * 않으면 null 혹은 0, false 같은 값이 된다.
 *
 * <h2>@Singular</h2>
 * 컬렉션에 <code>@Singular</code> 애노테이션을 지정하면 builder 에서 setter 대신에 adder 를 2개 만드게 된다.
 * 하나는 단일 값 추가, 다른 하나는 컬렉션 전체값을 모두 추가하는 것으로 만든다.
 * 또한 <code>clear</code> 메소드도 만들어진다. 기존값을 모두 삭제한다.
 * Map에 대해 @Singular를 지정하면 key, value를 인자로 받는 adder를 만든다.
 *
 * - {@code build} 호출시 생성되는 컬렉션은 불변이다.
 * - {@code build} 호출 이후 adder나 clear 메소드 호출은 기존값을 바꾸지 않는다.
 *
 * adder 메소드 이름은 필드이름이 영어 복수형이면 자동으로 영어 단수형으로 만들게 되는데 모호하거나 명시하고 싶을 때는
 * <code>@Singular('axis') List axes;</code> 처럼 명시할 수 있다.
 *
 * configurations
 * lombok.builder.flagUsage = [warning | error] (default: not set)
 * lombok.singular.useGuava = [true | false] (default: false) : java의 Collections.unmodifiableXXX 대신 guava ImmutableXXX 사용해서 구현
 * lombok.singular.auto = [true | false] (default: true) : singular 이름을 자동으로 생성(가능한 경우만), false 이면 항상 명시해줘야함.
 */
@ToString
@Builder(toBuilder = true)
public class BuilderExample {
    private String name;

    @Builder.ObtainVia(method = "doubleAge")
    private int age;

    @Singular
    private Set<String> occupations;

    @Singular("map")
    private Map<String, Integer> someMap;

    @Builder.Default // 이를 지정하지 않으면 Builder 사용하고 값을 명시하지 않을 때 null이 들어감.
    private LocalDateTime createdAt = LocalDateTime.now();

    public BuilderExample() {
    }

    public BuilderExample(String name, int age, Set<String> occupations, Map<String, Integer> someMap, LocalDateTime createdAt) {
        this.name = name;
        this.age = age;
        this.occupations = occupations;
        this.someMap = someMap;
        this.createdAt = createdAt;
    }

    @Builder(builderClassName = "BuilderExampleBuilder2", builderMethodName = "builder2")
    public static BuilderExample newInstance(String name, int age, @Singular Set<String> occupations) {
        BuilderExample builderExample = new BuilderExample();
        builderExample.name = name;
        builderExample.age = age * 3;
        builderExample.occupations = occupations;

        return builderExample;
    }

    public int doubleAge() {
        return age * 2;
    }
    public static void main(String[] args) {
        final BuilderExample example = BuilderExample.builder()
            .name("lombok")
            .age(5)
            .occupation("a")
            .occupation("b")
            .map("key1", 1)
            .map("key2", 2)
            .build();

        System.out.println("example : " + example);

        final BuilderExample example2 = example.toBuilder().occupation("c").build();
        System.out.println("example2 : " + example2); // ObtainVia 때문에 age가 2배가 됨.

        final BuilderExample build2 = BuilderExample.builder2()
            .name("Builder2")
            .age(3)
            .occupation("가")
            .occupation("나")
            .build();
        System.out.println("build2 example : " + build2); // age 가 3배로 나옴.

    }
}
