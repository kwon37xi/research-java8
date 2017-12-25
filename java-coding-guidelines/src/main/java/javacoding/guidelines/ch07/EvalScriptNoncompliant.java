package javacoding.guidelines.ch07;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class EvalScriptNoncompliant {

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

    // 원래 의도는 이름을 출력하는 것 뿐임. 그러나 서버의 파일을 조작하는 코드가 주입됨.
    private static void evalScript(String firstName) throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        String script = "print('" + firstName + "')";
        engine.eval(script);
    }
}
