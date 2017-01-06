package lombokpractice.tostring;

/*
https://projectlombok.org/features/ToString.html
toString() 메소드 생성 : 기본적으로 클래스 이름과 각 필드를 순서대로 comma 로 구분해 출력.

- includeFieldNames = true : 필드 이름 출력. default true.
- 기본적으로 모든 non static 필드 출력.
- exclude : 출력 제외할 필드, 나머지는 모두 출력
- of : 출력할 필드, 나머지는 출력 제외
- callSuper = true : superclass 구현도 포함해서 출력. 다른 클래스를 상속하지 않은 경우에는 사용하지 말것. 안그러면 java.lang.Object 내용 출력

configurations
* lombok.toString.includeFieldNames = [true | false] (default: true)
* lombok.toString.doNotUseGetters = [true | false] (default: false)
* lombok.toString.flagUsage = [warning | error] (default: not set)
*/

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = "id")
public class ToStringExample {
    private static final int STATIC_VAR = 10;

    private String name;
    private Shape shape = new Square(5, 10);
    private String[] tags;
    private int id;


    @ToString
    public static class Shape {
        private String name;

        public Shape(String name) {
            this.name = name;
        }
    }

    @ToString(callSuper = true, includeFieldNames = true)
    public static class Square extends Shape {
        private final int width, height;

        public Square(int width, int height) {
            super("Square");
            this.width = width;
            this.height = height;
        }
    }

    public static void main(String[] args) {
        ToStringExample ex = new ToStringExample();
        ex.setName("ToString first Example");
        ex.setTags(new String[]{"shape", "square", "circle", "triangle"});
        System.out.println(ex);
    }
}

