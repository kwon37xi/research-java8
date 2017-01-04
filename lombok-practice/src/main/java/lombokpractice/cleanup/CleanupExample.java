package lombokpractice.cleanup;

import lombok.Cleanup;

import java.io.*;

/*
@Cleanup https://projectlombok.org/features/Cleanup.html
자동으로 close() 메소드 호출해줌. finally block에서.
- local 변수에만.
- @Cleanup("dispose") 처럼, close 외의 메소드 지정가능.
- cleanup method는 인자가 없어야함.

* configuration
lombok.cleanup.flagUsage = [warning | error] (default: not set)

 */
public class CleanupExample {
    public static void main(String[] args) throws IOException {
        @Cleanup InputStream in = new FileInputStream("lombok-practice/README.md");
        @Cleanup OutputStream out = new FileOutputStream("/tmp/copied_readme.md");

        byte[] b = new byte[10000];
        while (true) {
            int r = in.read(b);
            if (r == -1) break;
            out.write(b, 0, r);
        }

    }
}
