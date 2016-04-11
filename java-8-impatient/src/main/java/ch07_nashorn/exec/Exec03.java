package ch07_nashorn.exec;

import ch07_nashorn.Lecture02;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Exec03 {
    public static void main(String[] args) throws ScriptException {
        final ScriptEngine engine = Lecture02.getEngine();
        engine.eval(Lecture02.getJs("exec/exec03.js"));
    }
}
