package com.jaitlpro.usercryptodb.crypt;

import org.apache.log4j.Logger;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class RSACrypt {

    static final Logger log = Logger.getLogger(RSACrypt.class);

    public static byte[] encryptAESKey(SecretKey AESKey) {

        byte[] AESKeyByte = secretKeyToByte(AESKey);
        
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException", e);
        }

        try {
            cipher.init(Cipher.ENCRYPT_MODE, RSAKeyLoader.loadPublicKey());
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException", e);
        }

        byte[] cipherText = new byte[0];
        try {
            cipherText = cipher.doFinal(AESKeyByte);
            log.info("Encrypt AES key with RSA");
        } catch (IllegalBlockSizeException e) {
            log.error("IllegalBlockSizeException", e);
        } catch (BadPaddingException e) {
            log.error("BadPaddingException", e);
        }

        return cipherText;
    }

    public static SecretKey decryptAESKey(byte[] key) {

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException", e);
        }

        try {
            cipher.init(Cipher.DECRYPT_MODE, RSAKeyLoader.loadPrivateKey());
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException", e);
        }

        byte[] newPlainText = new byte[0];

        try {
            newPlainText = cipher.doFinal(key);
            log.info("Decrypt AES key with RSA");
        } catch (IllegalBlockSizeException e) {
            log.error("IllegalBlockSizeException", e);
        } catch (BadPaddingException e) {
            log.error("BadPaddingException", e);
        }

        return secretKeyFromByte(newPlainText);
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
