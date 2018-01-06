package javacoding.guidelines.ch11;

import sun.security.rsa.RSAPrivateKeyImpl;

import java.security.Key;
import java.security.interfaces.RSAKey;
import java.security.interfaces.RSAPrivateKey;
import java.util.Arrays;

/**
 * 암호키같은 복학접 객체를 비교하는데 equals()를 사용하지 말라.
 * 잘못된 값이 반환된다.
 * 대부분의 Key 클래스는 Object.equals()를 오버라이드하지 않고 있다.
 * 따라서 동일한 값을 가지고 있을 때도 false를 리턴한다.
 * <p>
 * 일단 equals()로 비교하여 true이면 true로 간주하고, 그렇지 않으면 키를 encode한 값을 다시 비교한다.
 *
 * @see Key
 */
public class DoNotCompareKeyWithEquals {

    public static boolean keysEqual(Key key1, Key key2) {
        if (key1.equals(key2)) {
            return true;
        }

        if (Arrays.equals(key1.getEncoded(), key2.getEncoded())) {
            return true;
        }

        // 좀더 복잡한 Key 검증 로직...

        if ((key1 instanceof RSAPrivateKey) && (key2 instanceof RSAPrivateKey)) {
            if ((((RSAKey) key1).getModulus().equals(((RSAKey) key2).getModulus())) &&
                (((RSAPrivateKey) key1).getPrivateExponent().equals(((RSAPrivateKey) key2).getPrivateExponent()))) {
                return true;
            }
        }
        return false;
    }
}
