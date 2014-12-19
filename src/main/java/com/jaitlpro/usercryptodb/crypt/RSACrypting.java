package com.jaitlpro.usercryptodb.crypt;

import com.jaitlpro.usercryptodb.crypt.key.RSAKey;
import org.apache.log4j.Logger;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class RSACrypting {

    static final Logger log = Logger.getLogger(RSACrypting.class);

    public static byte[] encryptAESKey(byte[] key) {
        
        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException", e);
        }

        try {
            cipher.init(Cipher.ENCRYPT_MODE, RSAKey.getPublicKey());
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException", e);
        }

        byte[] cipherText = new byte[0];
        try {
            cipherText = cipher.doFinal(key);
            log.info("Encrypt AES key with RSA");
        } catch (IllegalBlockSizeException e) {
            log.error("IllegalBlockSizeException", e);
        } catch (BadPaddingException e) {
            log.error("BadPaddingException", e);
        }

        return cipherText;
    }

    public static byte[] decryptAESKey(byte[] key) {

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException", e);
        }

        try {
            cipher.init(Cipher.DECRYPT_MODE, RSAKey.getPrivateKey());
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

        return newPlainText;
    }
}
