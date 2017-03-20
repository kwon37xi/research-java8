package lombokpractice.constructors;

import lombok.*;

/**
 * https://projectlombok.org/features/Constructor.html
 *
 * {@code @NoArgsConstructor} : 파라미터 없는 기본 생성자. final 필드가 있을 경우에는 오류가 발생한다.
 * 하지만 @NoArgsConstructor(force=true)하면 0, false, null 등으로 초기화하는 생성자를 만든다.
 * {@code @NonNull}필드에 대해서는 아무것도 검사하지 않으므로 주의해야한다.
 * 일부 Hibernate나 Service Provider 인터페이스등의 자바 프레임워크는 기본 생성자가 필수이다.
 *
 * {@code RequiredArgsConstructor} 초기화 안된 final 필드와 선언시 초기화 안된 {@code @NonNull} 마크된 필드에 대한 생성자를 만든다.
 * {@code @NonNull}에 대해서는 null 검사도 한다. 따라서 생성자에 null을 넘기면 {@code NullPointerException}이 발생한다.
 *
 * {@code @AllArgsConstructor} 모든 필드에 대한 생성자 생성. {@code @NonNull} 필드는 null 검사.
 *
 * {@code staticName 옵션} : private 으로 생성자를 만들고, 해당 생성자를 호출하는 static method를 만듬.
 *
 * {@code onConstructor=@__({@AnnotationsHere})} 로 생성자에 어노테이션 추가.
 *
 * static 필드는 모두 무시된다. 모든 자동생성된 생성자에는 {@code java.beans.ConstructorProperties} 가 붙는다. 이를통해
 * Bean Editor가 올바른 생성자를 판단할 수 있게 된다. 이는 Java 1.6 부터 존재하므로 1.5에서는 컴파일 오류가 발생한다.
 * {@code @AllArgsConstructor(suppressConstructorProperties=true)} 로 무시하게 할 수 있다.
 *
 * 이미 생성자가 존재하더라도 생성자를 만든다. 동일 생성자가 이미 존재하면 컴파일 오류가 발생한다.
 *
 * configurations
 * lombok.anyConstructor.suppressConstructorProperties = [true|false] {@code java.beans.ConstructorProperties} 안생성하기 여부. 기본 false
 * lombok.[allArgsConstructor|requiredArgsConstructor|noArgsConstructor].flagUsage = [warning | error]
 * lombok.anyConstructor.flagUsage = [warning | error] : 전체 생성자에 대한 경고 flag
 */

@RequiredArgsConstructor(staticName = "of")
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ConstructorExample<T> {
    private int x, y;
    @NonNull private T description;

    @NoArgsConstructor
    @ToString
    public static class NoArgsExample {
        @NonNull private String field;
    }

    public static void main(String[] args) {
        final ConstructorExample<String> ce = ConstructorExample.of("Hello Lombok!");
        System.out.println("RequiredArgsConstructor : " + ce);

        final ConstructorExample<String> ce2 = new ConstructorExample<>(1, 2, "Haha");
        System.out.println("AllArgsConstructor : " + ce2);

        NoArgsExample ne = new NoArgsExample();
        System.out.println("NoArgsConstructor : " + ne); // null 상태로 남아있음.
    }
}
