package ch03_lambda_library;

import javafx.scene.paint.Color;

import java.util.function.UnaryOperator;

public class Lecture04 {

    // 함수형 인터페이스의 객체를 리턴하는 메소드
    public static UnaryOperator<Color> brighten(double factor) {
        return c -> c.deriveColor(0, 1, factor, 1);
    }
}
