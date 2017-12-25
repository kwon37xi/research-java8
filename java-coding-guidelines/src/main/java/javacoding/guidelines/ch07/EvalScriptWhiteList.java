package javacoding.guidelines.ch07;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class EvalScriptWhiteList {

    public static void main(String[] args) throws ScriptException {
        String script = "dummy');" +
            "var imports = new JavaImporter(java.io);\n" +
            "with(imports) {\n" +
            "    bwr = new BufferedWriter(new FileWriter(\"/tmp/config.cfg\"));\n" +
            "    bwr.write(\"some text\"); bwr.close();\n" +
            "}" +
            "//;";
        evalScript(script);


    }

    // 입력 가능한 문자 제약
    private static void evalScript(String firstName) throws ScriptException {
        if (!firstName.matches("[\\w]*")) {
            throw new IllegalArgumentException("적절하지 않은 문자열입니다.");
        }
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        String script = "print('" + firstName + "');";
        engine.eval(script);
    }
}
