package com.jaitlpro.usercryptodb.crypto.key;

import java.io.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by Igor on 18.12.2014.
 */
public class RSAKey {
    public static PublicKey getPublicKey() {

        byte[] keyByteArr = getKeyAsBytes("public");

        KeyFactory kf = null;
        try {
            kf = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        PublicKey publicKey = null;

        try {
            publicKey = kf.generatePublic(new X509EncodedKeySpec(keyByteArr));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return publicKey;
    }

    public static PrivateKey getPrivateKey() {
        byte[] keyByteArr = getKeyAsBytes("private");

        KeyFactory kf = null;
        try {
            kf = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        PrivateKey privateKey = null;

        try {
            privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(keyByteArr));
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }

        return privateKey;
    }

    private static byte[] getKeyAsBytes(String type) {
        ClassLoader classLoader = new RSAKey().getClass().getClassLoader();
        InputStream fileStream = classLoader.getResourceAsStream("rsa/" + type + ".key");

        byte[] keyByteArr = null;

        try {
            keyByteArr = getBytes(fileStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return keyByteArr;
    }

    private static byte[] getBytes(InputStream is) throws IOException {

        int len;
        int size = 1024;
        byte[] buf;

        if (is instanceof ByteArrayInputStream) {
            size = is.available();
            buf = new byte[size];
            len = is.read(buf, 0, size);
        } else {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            buf = new byte[size];
            while ((len = is.read(buf, 0, size)) != -1)
                bos.write(buf, 0, len);
            buf = bos.toByteArray();
        }
        return buf;
    }
}
