package ch07_nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Lecture03 {
    public static void main(String[] args) throws ScriptException {
        final ScriptEngine engine = Lecture02.getEngine();

        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        engine.put("list", list);
        engine.eval("list['remove(Object)'](1)");

        System.out.println(list); // 2, 3
    }
}
