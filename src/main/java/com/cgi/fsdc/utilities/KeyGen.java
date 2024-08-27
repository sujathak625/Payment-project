package com.cgi.fsdc.utilities;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class KeyGen {

    public static void main(String[] args) throws Exception {
        // Generate a new AES key
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256); // 128, 192, or 256 bits for AES
        SecretKey secretKey = keyGenerator.generateKey();

        // Encode the key as a Base64 string
        String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());

        // Print the key, which can be stored in a properties file
        System.out.println("Generated AES Key (Base64): " + encodedKey);
    }
}