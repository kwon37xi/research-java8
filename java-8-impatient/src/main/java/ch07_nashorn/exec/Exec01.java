package ch07_nashorn.exec;

import ch07_nashorn.Lecture02;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * jjs 로 하는게 쉽다. 하지만.. 자동완성이 안된다.
 */
public class Exec01 {
    public static void main(String[] args) throws ScriptException {
        final ScriptEngine engine = Lecture02.getEngine();
        engine.eval(Lecture02.getJs("exec/exec01.js"));
    }
}
