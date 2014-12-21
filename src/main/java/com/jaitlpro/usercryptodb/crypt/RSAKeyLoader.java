package com.jaitlpro.usercryptodb.crypt;

import org.apache.log4j.Logger;

import java.io.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class RSAKeyLoader {

    static final Logger log = Logger.getLogger(RSAKeyLoader.class);

    public static PublicKey loadPublicKey() {
        log.info("Get public key from file");

        byte[] keyByteArr = getKeyAsBytes("public");

        KeyFactory kf = null;
        try {
            kf = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
        }

        PublicKey publicKey = null;

        try {
            publicKey = kf.generatePublic(new X509EncodedKeySpec(keyByteArr));
        } catch (InvalidKeySpecException e) {
            log.error("InvalidKeySpecException", e);
        }

        return publicKey;
    }

    public static PrivateKey loadPrivateKey() {
        log.info("Get private key from file");

        byte[] keyByteArr = getKeyAsBytes("private");

        KeyFactory kf = null;

        try {
            kf = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            log.error("NoSuchAlgorithmException", e);
        }

        PrivateKey privateKey = null;

        try {
            privateKey = kf.generatePrivate(new PKCS8EncodedKeySpec(keyByteArr));
        } catch (InvalidKeySpecException e) {
            log.error("InvalidKeySpecException", e);
        }

        return privateKey;
    }

    private static byte[] getKeyAsBytes(String type) {
        log.info(String.format("Read file with %s key", type));

        ClassLoader classLoader = new RSAKeyLoader().getClass().getClassLoader();
        InputStream fileStream = classLoader.getResourceAsStream("rsa/" + type + ".key");

        byte[] keyByteArr = null;

        try {
            keyByteArr = getBytes(fileStream);
        } catch (IOException e) {
            log.error("IOException", e);
        }

        return keyByteArr;
    }

    private static byte[] getBytes(InputStream is) throws IOException {

        log.info("Get byte[] from InputStream");

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
