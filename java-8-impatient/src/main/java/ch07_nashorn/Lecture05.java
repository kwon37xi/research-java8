package ch07_nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 *
 */
public class Lecture05 {
    public static void main(String[] args) throws ScriptException {
        final ScriptEngine engine = Lecture02.getEngine();
        engine.eval("print('Hello'.slice(-2))");
        engine.eval("print('Hello'.compareTo('World'))");
        engine.eval("print('Hello'.slice('-2'))"); // '-2'가 -2로 자동 변환됨.
    }
}
