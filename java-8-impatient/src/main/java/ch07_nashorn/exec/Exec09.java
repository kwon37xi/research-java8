package ch07_nashorn.exec;

import javax.script.ScriptException;
import java.io.File;
import java.io.IOException;

/**
 * 명령행인자, 환경변수, readLine 으로 입력받기
 */
public class Exec09 {
    public static void main(String[] args) throws ScriptException, IOException, InterruptedException {
        String jjs = System.getProperty("java.home") + "/bin/jjs";
        ProcessBuilder builder = new ProcessBuilder(jjs, "-scripting", "exec09.js")
            .directory(new File("java-8-impatient/src/main/resources/ch07_nashorn/exec"))
            .redirectOutput(ProcessBuilder.Redirect.INHERIT)
            .redirectInput(ProcessBuilder.Redirect.INHERIT);

//        builder.environment().put("AGE", "27"); // 환경변수도 작동함.

        final Process process = builder.start();
        process.waitFor();
    }
}
