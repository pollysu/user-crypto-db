package com.jaitlpro.usercryptodb.crypt;

import com.jaitlpro.usercryptodb.entry.UserCryptEntry;
import com.jaitlpro.usercryptodb.entry.UserEntry;
import org.apache.log4j.Logger;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESCrypt {

    static final Logger log = Logger.getLogger(AESCrypt.class);

    public static UserCryptEntry encryptUser(UserEntry user) {

        log.info(String.format("Encrypt user: %s", user.getLogin()));

        SecretKey AESKey = generateAESKey();

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException", e);
        }

        try {
            cipher.init(Cipher.ENCRYPT_MODE, AESKey);
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException", e);
        }

        byte[] cipherData = null;

        try {
            cipherData = cipher.doFinal(user.toXML().getBytes("UTF8"));
            log.info(String.format("Encrypt user data: %s", user.getLogin()));
        } catch (IllegalBlockSizeException e) {
            log.error("IllegalBlockSizeException", e);
        } catch (BadPaddingException e) {
            log.error("BadPaddingException", e);
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException", e);
        }

        UserCryptEntry cryptUser = new UserCryptEntry();

        cryptUser.setCryptData(cipherData);

        log.info(String.format("Encrypt user AES key with RSA: %s", user.getLogin()));

        byte[] AESKeyCrypt = RSACrypt.encryptAESKey(AESKey);

        cryptUser.setCryptKey(AESKeyCrypt);
        cryptUser.setLogin(user.getLogin());

        log.info(String.format("Send encrypt user: %s", cryptUser.getLogin()));
        return cryptUser;
    }

    public static UserEntry decryptUser(UserCryptEntry cryptUser) {

        log.info(String.format("Decrypt user: %s", cryptUser.getLogin()));

        SecretKey AESKey = RSACrypt.decryptAESKey(cryptUser.getCryptKey());

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException", e);
        }

        try {
            cipher.init(Cipher.DECRYPT_MODE, AESKey);
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException", e);
        }

        byte[] newPlainText = new byte[0];

        try {
            newPlainText = cipher.doFinal(cryptUser.getCryptData());
            log.info(String.format("Decrypt user data: %s", cryptUser.getLogin()));
        } catch (IllegalBlockSizeException e) {
            log.error("IllegalBlockSizeException", e);
        } catch (BadPaddingException e) {
            log.error("BadPaddingException", e);
        }

        String UserEntryXML = null;

        try {
            UserEntryXML = new String(newPlainText, "UTF8");
        } catch (UnsupportedEncodingException e) {
            log.error("UnsupportedEncodingException", e);
        }

        log.info(String.format("Send decrypt user: %s", cryptUser.getLogin()));
        return UserEntry.getUserEntryFromXML(UserEntryXML);
    }

    private static SecretKey generateAESKey() {

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
}
