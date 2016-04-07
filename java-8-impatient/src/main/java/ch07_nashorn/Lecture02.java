package ch07_nashorn;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 */
public class Lecture02 {
    public static void main(String[] args) throws ScriptException {
        ScriptEngine engine = getEngine();
        Object result = engine.eval("'Hello, World!'.length");
        System.out.println(result);

        final Bindings scope = engine.createBindings();
        scope.put("greeting", "안녕하세요~");
        engine.eval("print(greeting);", scope);
    }

    public static ScriptEngine getEngine() {
        ScriptEngineManager sem = new ScriptEngineManager();
        return sem.getEngineByName("nashorn");
    }
}
