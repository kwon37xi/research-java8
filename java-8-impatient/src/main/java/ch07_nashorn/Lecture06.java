package ch07_nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 *
 */
public class Lecture06 {
    public static void main(String[] args) throws ScriptException {
        final ScriptEngine engine = Lecture02.getEngine();
        engine.eval("print('Hello'.slice(-2.99))"); // -2로 자동 변환
        engine.eval("var price=10 ; print(java.lang.String.format('Unit price: %.2f', Number(price)))");
    }
}
