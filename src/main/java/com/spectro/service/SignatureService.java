package com.spectro.service;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * Created by farhans on 11/18/18.
 */
@Service
public class SignatureService {

    @Value("${spectro.privatekey}")
    private String privateKeyLocation;

    @Value("${spectro.publickey}")
    private String publicKeyLocation;

    @Autowired
    ResourceLoader resourceLoader;

    private static final String TYPE = "SHA1withRSA";

    public String getSignature(String parameters) {

        Signature ourSign = null;
        try {
            ourSign = Signature.getInstance(TYPE);
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

    public boolean validate(String formattedValue, String responseSign)
            throws IOException, NoSuchAlgorithmException, InvalidKeyException, SignatureException, InvalidKeySpecException {

        Signature verifier = Signature.getInstance(TYPE);
        verifier.initVerify(loadPublicKey());
        verifier.update(formattedValue.getBytes());

        byte[] bytes = new BASE64Decoder().decodeBuffer(responseSign);

        return verifier.verify(bytes);
    }


    private PrivateKey loadPKC8EncodedPrivateKey() throws Exception {

        PKCS8EncodedKeySpec privSpec = new PKCS8EncodedKeySpec(getKeyBytes(privateKeyLocation));
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey ourKey = keyFactory.generatePrivate(privSpec);
        return ourKey;
    }

    public PublicKey loadPublicKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        String keyValue = new String(getKeyBytes(publicKeyLocation));

        keyValue = keyValue.replace("-----BEGIN PUBLIC KEY-----", "");
        keyValue = keyValue.replace("-----END PUBLIC KEY-----", "");

        byte[] keyBytes = new BASE64Decoder().decodeBuffer(keyValue);

        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }


    private byte[] getKeyBytes(String location) {
        try {
            return Files.readAllBytes(Paths.get(location));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
