package br.com.locadora.Util;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Cripto {

    public static String criptografa(String criptografar) {
        try {
            return criptografa(criptografar, "bHfHDPxPffCQImLF");
        } catch (Exception e) {
            return null;
        }
    }

    public static String criptografa(String criptografar, String key) throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        if (criptografar == null) {
            return null;
        }
        MessageDigest MD5 = MessageDigest.getInstance("MD5");
        byte[] keyB = MD5.digest(key.getBytes());
        SecretKeySpec keyS = new SecretKeySpec(keyB, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyB);
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, keyS, ivSpec);
        Base64.Encoder encoder = Base64.getEncoder();
        byte[] criptografado = encoder.encode(cipher.doFinal(criptografar.getBytes()));
        return new String(criptografado);
    }

    public static String desCriptografa(String descriptografar, String key) throws NoSuchPaddingException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException {
        if (descriptografar == null) {
            return null;
        }
        MessageDigest MD5 = MessageDigest.getInstance("MD5");
        byte[] keyB = MD5.digest(key.getBytes());
        SecretKeySpec keyS = new SecretKeySpec(keyB, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyB);
        Cipher cipher = Cipher.getInstance("AES/CTR/NoPadding");
        cipher.init(Cipher.DECRYPT_MODE, keyS, ivSpec);
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] descriptografado = cipher.doFinal(decoder.decode(descriptografar.getBytes()));
        return new String(descriptografado);
    }
}
