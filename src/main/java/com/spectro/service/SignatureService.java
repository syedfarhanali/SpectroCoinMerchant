package com.spectro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * Created by farhans on 11/18/18.
 */
@Service
public class SignatureService {

    @Value("${spectro.privatekey}")
    private String propFileLocation;

    @Autowired
    ResourceLoader resourceLoader;

    public String getSignature(String parameters) {

        Signature ourSign = null;
        try {
            ourSign = Signature.getInstance("SHA1withRSA");
            ourSign.initSign(loadPKC8EncodedPrivateKey());
            ourSign.update(parameters.getBytes());
            String sig = new BASE64Encoder().encode(ourSign.sign());
            String signature = sig.replace("\n", "").replace("\r", "");
            return signature;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    private PrivateKey loadPKC8EncodedPrivateKey() throws Exception {

        PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(getKeyBytes());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey ourKey = keyFactory.generatePrivate(privSpec);
        return ourKey;
    }

    private byte[] getKeyBytes() {
        try {
            return Files.readAllBytes(Paths.get(propFileLocation));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
