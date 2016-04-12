package ch07_nashorn.exec;

import ch07_nashorn.Lecture02;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 * 다른 문자열에서 부분 문자열(substring)을 추출하여 nonliteral javascript 문자열을 만들고, getClass 메서드를 호출한다.
 * 어떤 클래스를 얻게 되는가?
 * 다음으로 객체를 java.lang.String.class.cast에 전달한다. 무슨일이 일어나고 그 원인은?
 *
 * --> java.lang.String 을 얻게되고 cast를 해도 아무일도 안 일어난다.
 * 183쪽,
 * "일반적으로 javascript 문자열은 자바 메서드에 전달될 때 자바 문자열로 변환된다."
 * "모든 javascript 객체는 String 파라미터를 받는 자바 메서드에 전달될 때 문자열로 변환된다는 점도 유념하기 바란다" 때문인듯.
 */
public class Exec04 {
    public static void main(String[] args) throws ScriptException {
        final ScriptEngine engine = Lecture02.getEngine();
        engine.eval(Lecture02.getJs("exec/exec04.js"));
    }
}
