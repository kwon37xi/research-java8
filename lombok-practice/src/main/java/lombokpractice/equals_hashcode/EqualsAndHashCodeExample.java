package lombokpractice.equals_hashcode;

/*
 * https://projectlombok.org/features/EqualsAndHashCode.html
 *
 * equals() hashCode() 메소드 생성. : 기본적으로 non-static, non-transient 필드 모두에 대해 생성. exclude/of 가능.
 *
 * 상속받은 subclass 에 대해 @EqualsHashCode 는 문제 소지가 있음. 일반적으로 이런 상황은 피해야 함. callSuper = true를 성정하여
 * 수퍼 클래스의 equals/hashCode를 포함하게 할 수 있음. 단, 모든 equals 구현이 이 상황을 올바로 처리하지는 못함. 하지만 lombok이 생성한 equals는 처리할 수 있음.
 *
 * 아무것도 상속하지 않은 상태에서 callSuper=true는 컴파일 오류를 발생시킴.
 *
 * lombok 0.10 부터 JPA 프록시에서 잘 동작할 수 있도록 canEqual를 생성함. http://www.artima.com/lejava/articles/equality.html 참조.
 */
public class EqualsAndHashCodeExample {
}
