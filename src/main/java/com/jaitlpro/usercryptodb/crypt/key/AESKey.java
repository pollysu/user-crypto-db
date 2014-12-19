package com.jaitlpro.usercryptodb.crypt.key;

import org.apache.log4j.Logger;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

public class AESKey {

    static final Logger log = Logger.getLogger(AESKey.class);

    public static SecretKey generateAESKey() {

        log.info("Generate AES key");

        KeyGenerator generator = null;
        try {
            generator = KeyGenerator.getInstance("AES");

        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
        }

        generator.init(128);

        return generator.generateKey();
    }

    public static byte[] secretKeyToByte(SecretKey key) {
        log.info("Secret key from SecretKey to byte[]");

        return key.getEncoded();
    }

    public static SecretKey secretKeyFromByte(byte[] key) {
        log.info("Secret key from byte[] to SecretKey");

        return new SecretKeySpec(key, "AES");
    }

}
