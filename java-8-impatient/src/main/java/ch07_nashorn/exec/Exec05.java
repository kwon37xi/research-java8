package ch07_nashorn.exec;

import ch07_nashorn.Lecture02;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * 189 페이지를 참조해 ArrayList를 확장해 모든 add 호출 로그를 남길 수 있도록 해주는 Factory Javascript 함수를 작성하라.
 */
public class Exec05 {
    public static void main(String[] args) throws ScriptException {
        final ScriptEngine engine = Lecture02.getEngine();
        engine.eval(Lecture02.getJs("exec/exec05.js"));
    }
}
