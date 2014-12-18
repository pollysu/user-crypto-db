package com.jaitlpro.usercryptodb.crypto;

import com.jaitlpro.usercryptodb.entry.UserCryptoEntry;
import com.jaitlpro.usercryptodb.entry.UserEntry;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class CryptingUser {

    public static UserCryptoEntry encryptUser(UserEntry user) {

        UserCryptoEntry cryptoUser = new UserCryptoEntry();

        SecretKey key = AESKey.generateAESKey();

        cryptoUser.setCryptoKey(AESKey.secretKeyToByte(key));
        cryptoUser.setLogin(user.getLogin());

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

        cryptoUser.setCryptoData(cipherData);

        return cryptoUser;
    }

    public static UserEntry decryptUser(UserCryptoEntry cryptoUser) {

        SecretKey key = AESKey.secretKeyFromByte(cryptoUser.getCryptoKey());

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
            newPlainText = cipher.doFinal(cryptoUser.getCryptoData());
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
