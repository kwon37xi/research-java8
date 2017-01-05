package lombokpractice.gettersetteraccessor;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/*
https://projectlombok.org/features/experimental/Accessors.html

@Accessors : @Getter/@Setter 생성방식 설정.

GetterSetterExample 참조.

단, prefix : 가급적 하지말것. prefix 의 문자열을 별생각없이 변경할 경우, prefix에 해당하는 문자열로 시작하지 않는 필드의 Getter/Setter가 모두 사라질 수 있음.
  IDE 리팩토링시에도 prefix를 안 붙이고 리팩토링 할 경우 코드 다 깨짐.
 */
public class AccessorsExample {
    @Accessors(fluent=true)
    public static class AccessorsFluentExample {
        @Getter
        @Setter
        private int age = 10;
    }

    public static class PrefixExample {
        @Accessors(prefix = "_")
        @Getter
        @Setter
        private String _Name = "Hello world!";
    }

    public static void main(String[] args) {
        AccessorsFluentExample fluentEx = new AccessorsFluentExample();
        fluentEx.age(57);

        System.out.println(fluentEx.age());

        PrefixExample prefixEx = new PrefixExample();
        prefixEx.setName("Hello Java!");

        System.out.println(prefixEx.getName());
    }
}
