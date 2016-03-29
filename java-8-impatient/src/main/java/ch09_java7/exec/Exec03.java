package ch09_java7.exec;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 문제의 의도는,
 * catch 절에서 타입을 알 수 없는 형태로 "throw ex"를 하는데도 컴파일러가 자동으ㄹ
 * catch 절에서 던질 수 있는 예외의 종류를 판별하여 굳이 "throws Exception"으로 모든 예외를 잡지 않고
 * catch 절에서 잡은 예외들만 throws 에 기술해도 괜찮음을 보여주려는 것이다.
 */
public class Exec03 {
    public static void main(String[] args) throws FileNotFoundException, UnknownHostException {
        try {
            if ((Math.random() * 10) % 2 == 0) {
                throw new FileNotFoundException("file not found exception");
            } else {
                throw new UnknownHostException("unknown host exception");
            }
        } catch (FileNotFoundException | UnknownHostException ex) {
            Logger.getGlobal().log(Level.SEVERE, "some error", ex);
            throw ex;
        }
    }
}
