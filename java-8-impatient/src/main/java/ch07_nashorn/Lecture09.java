package ch07_nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 *
 */
public class Lecture09 {
    public static void main(String[] args) throws ScriptException {
        final ScriptEngine engine = Lecture02.getEngine();
        engine.eval(Lecture02.getJs("lecture09.js"));
    }
}
