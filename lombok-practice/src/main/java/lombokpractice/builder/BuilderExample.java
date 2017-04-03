package lombokpractice.builder;

/**
 * {@code @Builder} 객체 빌더를 만든다.
 *
 * https://projectlombok.org/features/Builder.html
 *
 * Class, 생성자, 혹은 메소드에 지정할 수 있다. Class나 생성자가 가장 일반적인 경우이다.
 *
 * 대상 Foo class 에 빌더를 지정하면
 * - FooBuilder를 만든다. {@code builder}라 부른다.
 * - builder에 대상의 각 대해 non-static, non-final 필드가 생긴다.
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
 *
 */
public class BuilderExample {
}
