package com.jaitlpro.usercryptodb.crypt;

import com.jaitlpro.usercryptodb.crypt.key.AESKey;
import com.jaitlpro.usercryptodb.entry.UserCryptEntry;
import com.jaitlpro.usercryptodb.entry.UserEntry;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class CryptingUser {

    public static UserCryptEntry encryptUser(UserEntry user) {

        UserCryptEntry cryptUser = new UserCryptEntry();

        SecretKey key = AESKey.generateAESKey();

        byte[] AESKeyByte = AESKey.secretKeyToByte(key);
        byte[] AESKeyCrypt = RSACrypting.encryptAESKey(AESKeyByte);

        cryptUser.setCryptoKey(AESKeyCrypt);
        cryptUser.setLogin(user.getLogin());

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        try {
            cipher.init(Cipher.ENCRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        byte[] cipherData = null;

        try {
            cipherData = cipher.doFinal(user.toXML().getBytes("UTF8"));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        cryptUser.setCryptoData(cipherData);

        return cryptUser;
    }

    public static UserEntry decryptUser(UserCryptEntry cryptUser) {

        byte[] AESKeyCrypt = cryptUser.getCryptKey();
        byte[] AESKeyByte = RSACrypting.decryptAESKey(AESKeyCrypt);

        SecretKey key = AESKey.secretKeyFromByte(AESKeyByte);

        Cipher cipher = null;
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        }

        try {
            cipher.init(Cipher.DECRYPT_MODE, key);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        byte[] newPlainText = new byte[0];

        try {
            newPlainText = cipher.doFinal(cryptUser.getCryptoData());
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        String UserEntryXML = null;

        try {
            UserEntryXML = new String(newPlainText, "UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return UserEntry.getUserEntryFromXML(UserEntryXML);
    }
}
