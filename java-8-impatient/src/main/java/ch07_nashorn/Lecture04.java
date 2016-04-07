package ch07_nashorn;

import javax.script.ScriptEngine;
import javax.script.ScriptException;

/**
 *
 */
public class Lecture04 {
    public static void main(String[] args) throws ScriptException {
        final ScriptEngine engine = Lecture02.getEngine();
        engine.eval("var javaNetPackage = java.net; print(javaNetPackage);");
        engine.eval("var URL = Java.type('java.net.URL'); print(URL)");
        engine.eval("var JMath = Java.type('java.lang.Math'); print(JMath)"); // javascript Math 와의 충돌회피
        engine.eval("print(JMath.floorMod(-3, 10))"); // javascript Math 와의 충돌회피
        engine.eval("var url = new URL('http://horstmann.com'); print(url)");
        engine.eval("var Entry = Java.type('java.util.AbstractMap$SimpleEntry'); print(Entry)");
    }
}
