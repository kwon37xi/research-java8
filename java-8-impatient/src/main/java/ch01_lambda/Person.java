package ch01_lambda;

public interface Person {
    long getId();

    default String getName() {
        return "John Q. Public";
    }
}
