package javacoding.guidelines.ch13;

import java.lang.reflect.Array;
import java.security.MessageDigest;

/**
 * 패스워드는 SHA-256 같은 강력한 해싱 알고리즘으로 salt 까지 더하여 해싱해야 한다.
 * <p>
 * 다른 예제는 그렇게 했지만 비밀번호를 문자열로 주고 받음으로써 heap dump 시 노출 위험을 가지고 있다.
 * 이 예제는 String으로 비밀번호를 받지 않고 배열로 받은뒤에 곧바로 데이터를 제거하는 방식으로 데이터 노출위험을 감소시켰다.
 */
public class PasswordHashCompliant {

    // String pass가 문제가 된다.
    private void setPassword(byte[] pass) throws Exception {
        byte[] salt = generateSalt(12);

        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");

        // 뭐지? 왜 String을 byte와 더하지? pass.getBytes() 와 salt를 합친 array로 digest해야함.
//        byte[] hashVal = messageDigest.digest((pass + salt).getBytes());

    }

    private byte[] generateSalt(int bytes) {
        // 마구 만든 salt임.
        return new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0, 1, 2};
    }

    public <T> T[] concatenate(T[] a, T[] b) {
        int aLen = a.length;
        int bLen = b.length;

        T[] c = (T[]) Array.newInstance(a.getClass().getComponentType(), aLen + bLen);
        System.arraycopy(a, 0, c, 0, aLen);
        System.arraycopy(b, 0, c, aLen, bLen);
        return c;
    }
}
