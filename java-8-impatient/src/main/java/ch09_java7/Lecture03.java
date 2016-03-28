package ch09_java7;

import java.util.Objects;

public class Lecture03 {
    public static class Person {
        private String first;
        private String last;

        @Override
        public boolean equals(Object otherObject) {
            if (this == otherObject) return true;
            if (otherObject == null) return false;
            if (getClass() != otherObject.getClass()) return false;

            Person other = (Person) otherObject;
            return Objects.equals(first, other.first) && Objects.equals(last, other.last);
        }

        @Override
        public int hashCode() {
            return Objects.hash(first, last); // Arrays.hashCode 연쇄 호출
        }
    }
}
