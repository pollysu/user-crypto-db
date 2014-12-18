package com.jaitlpro.usercryptodb.crypto;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Igor on 18.12.2014.
 */
public class AESKey {

    public static SecretKey generateAESKey() {

        SecretKey key = null;

        try {
            key =  KeyGenerator.getInstance("AES").generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return key;
    }

    public static byte[] secretKeyToByte(SecretKey key) {
        return key.getEncoded();
    }

    public static SecretKey secretKeyFromByte(byte[] key) {
        return new SecretKeySpec(key, "AES");
    }

}
