package ch07_nashorn.exec;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;

/**
 * 환경변수 값 출력
 */
public class Exec08 {
    public static void main(String[] args) throws ScriptException, IOException, InterruptedException {
        String jjs = System.getProperty("java.home") + "/bin/jjs";
        ProcessBuilder builder = new ProcessBuilder(jjs, "-scripting", "exec08.js")
            .directory(new File("java-8-impatient/src/main/resources/ch07_nashorn/exec"));
        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT);
        final Process process = builder.start();
        process.waitFor();
    }
}
