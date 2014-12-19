package com.jaitlpro.usercryptodb.crypt;

import com.jaitlpro.usercryptodb.crypt.key.AESKey;
import com.jaitlpro.usercryptodb.entry.UserCryptEntry;
import com.jaitlpro.usercryptodb.entry.UserEntry;
import org.apache.log4j.Logger;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CryptingUser {

    static final Logger log = Logger.getLogger(CryptingUser.class);

    public static UserCryptEntry encryptUser(UserEntry user) {

        log.info(String.format("Encrypt user: %s", user.getLogin()));

        UserCryptEntry cryptUser = new UserCryptEntry();

        SecretKey key = AESKey.generateAESKey();

        log.info(String.format("Encrypt user AES key with RSA: %s", user.getLogin()));
        byte[] AESKeyByte = AESKey.secretKeyToByte(key);
        byte[] AESKeyCrypt = RSACrypting.encryptAESKey(AESKeyByte);

        cryptUser.setCryptoKey(AESKeyCrypt);
        cryptUser.setLogin(user.getLogin());

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException", e);
        }

        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
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

        cryptUser.setCryptoData(cipherData);

        log.info(String.format("Send encrypt user: %s", cryptUser.getLogin()));
        return cryptUser;
    }

    public static UserEntry decryptUser(UserCryptEntry cryptUser) {

        log.info(String.format("Decrypt user: %s", cryptUser.getLogin()));

        log.info(String.format("Decrypt user AES key with RSA: %s", cryptUser.getLogin()));
        byte[] AESKeyCrypt = cryptUser.getCryptKey();
        byte[] AESKeyByte = RSACrypting.decryptAESKey(AESKeyCrypt);

        SecretKey key = AESKey.secretKeyFromByte(AESKeyByte);

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
        } catch (NoSuchPaddingException e) {
            log.error("NoSuchPaddingException", e);
        }

        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            log.error("InvalidKeyException", e);
        }

        byte[] newPlainText = new byte[0];

        try {
            newPlainText = cipher.doFinal(cryptUser.getCryptoData());
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
}
