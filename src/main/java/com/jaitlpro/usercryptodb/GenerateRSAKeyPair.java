package com.jaitlpro.usercryptodb;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Igor on 18.12.2014.
 */
public class GenerateRSAKeyPair {
    public static void main(String[] args) {
        KeyPairGenerator keyPairGenerator = null;

        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.genKeyPair();


        writeKeyToDisk(keyPair.getPublic(), "public.key");
        writeKeyToDisk(keyPair.getPrivate(), "private.key");
    }

    public static void writeKeyToDisk(Key key, String fileName) {

        try (FileOutputStream fos = new FileOutputStream("C:/rsa/" + fileName)) {
            fos.write(key.getEncoded());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
