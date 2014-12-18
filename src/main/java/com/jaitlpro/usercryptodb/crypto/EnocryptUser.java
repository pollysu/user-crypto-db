package com.jaitlpro.usercryptodb.crypto;

import com.jaitlpro.usercryptodb.entry.UserEntry;

import javax.crypto.*;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;

public class EnocryptUser {
    SecretKey key;

    public byte[] enocrypt(UserEntry user) {


        KeyGenerator keyGen = null;
        try {
            keyGen = KeyGenerator.getInstance("AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        keyGen.init(128);
        key = keyGen.generateKey();

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

        byte[] cipherText = null;

        try {
            cipherText = cipher.doFinal(user.toXML().getBytes("UTF8"));
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        System.out.println( "Finish encryption: " );
        try {
            System.out.println( new String(cipherText, "UTF8") );
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        return cipherText;
    }

    public String decrytpoUser(byte[] cipherText) {
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
            newPlainText = cipher.doFinal(cipherText);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }

        System.out.println( "Finish decryption: " );

        String user = null;

        try {
            user = new String(newPlainText, "UTF8");
            System.out.println( user);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return user;
    }

}
