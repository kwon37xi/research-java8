package javacoding.guidelines.ch12;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * DES는 안전하지 않고 약한 암호 함수이다. 사용하지 말 것.
 * DES는 Brute Force로 하루만에 깰 수 있다.
 *
 * 대신 AES를 사용하라.
 */
public class DoNotUseWeakEncryptionAlgorithmDes {

    public static final String STRING_TO_BE_ENCODED = "some string to be encrypted";

    public static void main(String[] args) throws Exception {

        testDES();
        testAES();
    }

    // 안전하지 않은 DES
    private static void testDES() throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        SecretKey key = KeyGenerator.getInstance("DES").generateKey();
        Cipher cipher = Cipher.getInstance("DES");
        cipher.init(Cipher.ENCRYPT_MODE, key);

        byte[] encoded = STRING_TO_BE_ENCODED.getBytes("UTF8");
        byte[] encrypted = cipher.doFinal(encoded);

        System.out.println("DES encrypted : " + Arrays.toString(encrypted));
    }

    // 안전한 AES
    private static void testAES() throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        KeyGenerator kgen = KeyGenerator.getInstance("AES");
        kgen.init(128); // JCE 확장을 받아서 설치해야만 192, 256을 사용할 수 있다.

        SecretKey skey = kgen.generateKey();

        byte[] raw = skey.getEncoded();
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);

        byte[] encoded = STRING_TO_BE_ENCODED.getBytes("UTF8");
        byte[] encryped = cipher.doFinal(encoded);

        System.out.println("AES encrypted : " + Arrays.toString(encryped));



    }

}
