package lombokpractice.nonnull;

import lombok.NonNull;

/*
https://projectlombok.org/features/NonNull.html

@lombok.NonNull 을 지정하면 해당 변수에 null 체크를 하고 null 이면 NullPointerException 예외 던짐.
생성자에서는 this(), super() 호출 직후에 코드 삽입됨.

* Configuration Keys
lombok.nonNull.exceptionType = [NullPointerException | IllegalArgumentException] (default: NullPointerException).
lombok.nonNull.flagUsage = [warning | error] (default: not set)
 */
public class NonNullExample {
    private String name;

    public NonNullExample(@NonNull Person person) {
        this.name = person.getName();
    }

    public static class Person {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {
        try {
            new NonNullExample(null);
        } catch (IllegalArgumentException e) {
            e.printStackTrace(System.out);
        }

        Person person = new Person();
        person.setName("Kitty");

        NonNullExample nonNullExample = new NonNullExample(person);
        System.out.println(nonNullExample.name);
    }
}
