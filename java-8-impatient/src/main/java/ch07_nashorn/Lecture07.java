package ch07_nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.IOException;

/**
 *
 */
public class Lecture07 {
    public static void main(String[] args) throws IOException, ScriptException {
        final ScriptEngine engine = Lecture02.getEngine();
        engine.eval(Lecture02.getJs("lecture07.js"));
    }
}
