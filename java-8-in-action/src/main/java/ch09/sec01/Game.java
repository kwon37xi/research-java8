package ch09.sec01;

import java.util.Arrays;
import java.util.List;

public class Game {
    public static void main(String[] args) {
        List<Resizable> resizableShapes
            = Arrays.asList(new Ellipse());
        Utils.paint(resizableShapes);
    }
}
