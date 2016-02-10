package ch01_lambda;

import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Lecture05 {
    public static void main(String[] args) {
        List<String> labels = Arrays.asList("hello", "world");
        Stream<Button> buttonStream = labels.stream().map(Button::new);
        // 위코드는 아래와 같다.
        buttonStream = labels.stream().map(str -> new Button(str));

        List<Button> buttons = buttonStream.collect(Collectors.toList());


        Button[] buttonArray = buttonStream.toArray(Button[]::new);
        // 위 코드는 아래와 같다.
        buttonArray = buttonStream.toArray((value) -> new Button[value]);
    }
}
