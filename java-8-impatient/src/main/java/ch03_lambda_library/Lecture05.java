package ch03_lambda_library;

import java.util.function.UnaryOperator;

public class Lecture05 {
    public static void main(String[] args) {
    }

    public static <T>UnaryOperator<T> compose(UnaryOperator<T> op1, UnaryOperator<T> op2) {
        return t -> op2.apply(op1.apply(t));
    }
}
