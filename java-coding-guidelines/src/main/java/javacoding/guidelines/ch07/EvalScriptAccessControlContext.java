package javacoding.guidelines.ch07;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.security.*;

public class EvalScriptAccessControlContext {
    private static class RestrictedAccessControlContext {
        private static final AccessControlContext INSTANCE;

        static {
            INSTANCE = new AccessControlContext(new ProtectionDomain[]{
                new ProtectionDomain(null, null) // no permissions
            });
        }
    }

    public static void main(String[] args) {
        String script = "dummy');" +
            "var imports = new JavaImporter(java.io);\n" +
            "with(imports) {\n" +
            "    bwr = new BufferedWriter(new FileWriter(\"/tmp/config.cfg\"));\n" +
            "    bwr.write(\"some text\"); bwr.close();\n" +
            "}" +
            "//;";
        evalScript(script);
    }

    // AccessControl 작동하지 않았음. 확인 필요.
    private static void evalScript(String firstName) {

        // Restrict permission using the two-argument form of doPrivileged()
        try {
            AccessController.doPrivileged((PrivilegedAction<Void>) () -> {
                String script = "print('" + firstName + "');";
                try {
                    ScriptEngineManager manager = new ScriptEngineManager();
                    ScriptEngine engine = manager.getEngineByName("javascript");
                    engine.eval(script);
                } catch (ScriptException e) {
                    throw new IllegalStateException(e);
                }
                return null;
            }, RestrictedAccessControlContext.INSTANCE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
