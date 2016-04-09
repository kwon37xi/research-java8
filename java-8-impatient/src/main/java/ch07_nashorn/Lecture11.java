package ch07_nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Lecture11 {
    public static void main(String[] args) throws ScriptException {
        final ScriptEngine engine = Lecture02.getEngine();
        engine.eval(Lecture02.getJs("lecture11.js"));
    }
}
