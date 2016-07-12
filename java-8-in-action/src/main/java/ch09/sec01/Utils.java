package ch09.sec01;

import java.util.List;

public class Utils {
    public static void paint(List<Resizable> resizables) {
        resizables.forEach(r -> {
            r.setAbsoluteSize(42, 42);
            r.draw();
        });
    }
}
