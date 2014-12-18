package com.jaitlpro.usercryptodb.crypt.key;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

public class AESKey {

    public static SecretKey generateAESKey() {
        KeyGenerator generator = null;
        try {
            generator = KeyGenerator.getInstance("AES");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        generator.init(128);

        return generator.generateKey();
    }

    public static byte[] secretKeyToByte(SecretKey key) {
        return key.getEncoded();
    }

    public static SecretKey secretKeyFromByte(byte[] key) {
        return new SecretKeySpec(key, "AES");
    }

}
