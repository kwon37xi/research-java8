package lombokpractice.equals_hashcode;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
 * Scala case 클래스와 롬복 @EqualsAndHashCode를 사용한 클래스를 믹스해서 상속했다면 작동에 문제가 없을 것이다.
 * 만약 직접 equals/hashCode를 수정하려고 한다면 canEqual을 오버라이드 해야한다.
 * onParam=@__({@AnnotationsHere}) 사용가능.
 *
 * configurations
 *
 * lombok.equalsAndHashCode.doNotUseGetters = [true | false] (default: false)
 * lombok.equalsAndHashCode.callSuper = [call | skip | warn] (default: warn)
 * lombok.equalsAndHashCode.flagUsage = [warning | error] (default: not set)
 */
@EqualsAndHashCode(exclude = {"id", "shape"})
public class EqualsAndHashCodeExample {
    private transient int transientVar = 10;
    private String name;
    private double score;
    private Shape shape = new Square(5, 10);
    private String[] tags;
    private int id;

    public String getName() {
        return this.name;
    }

    /*
    canEqual 이 아래처럼 생성됨.
    protected boolean canEqual(Object other) {
        return other instanceof EqualsAndHashCodeExample;
    }

    equals()에서 아래처럼 호출됨.

    public boolean equals(Object o) {
        ...
        if (!other.canEqual((Object)this)) return false;
        ...
    }
     */

    @EqualsAndHashCode
    public static class Shape {
        @Getter
        @Setter
        public String name;
    }

    @EqualsAndHashCode(callSuper = true)
    public static class Square extends Shape {
        private final int width, height;

        public Square(int width, int height) {
            this.width = width;
            this.height = height;
        }
    }

}
