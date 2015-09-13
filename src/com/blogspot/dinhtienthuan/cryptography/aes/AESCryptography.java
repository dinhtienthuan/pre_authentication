package com.blogspot.dinhtienthuan.cryptography.aes;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import com.blogspot.dinhtienthuan.cryptography.Cryptography;
import com.blogspot.dinhtienthuan.exception.CryptographyException;

public class AESCryptography implements Cryptography {
    private static final long serialVersionUID = 236245678277291894L;
    private static final String ALGORITHM_NAME = "AES";
    private static final String ALGORITHM_MODE = "ECB";
    private static final String ALGORITHM_PADDING = "PKCS5Padding";
    private static final String TRANSFORMATION = ALGORITHM_NAME + "/" + ALGORITHM_MODE + "/" + ALGORITHM_PADDING;
    private static String KEY = "";

    private static SecretKeySpec SECRET_KEY_SPEC = null;
    private static Cipher CIPHER = null;

    private static final SecretKeySpec getSecretKeySpec() throws DecoderException {
        if (SECRET_KEY_SPEC == null) {
            byte[] key = Hex.decodeHex(KEY.toCharArray());
            SECRET_KEY_SPEC = new SecretKeySpec(key, ALGORITHM_NAME);
        }
        return SECRET_KEY_SPEC;
    }

    private static final Cipher getCipher() throws NoSuchAlgorithmException, NoSuchPaddingException {
        if (CIPHER == null) {
            CIPHER = Cipher.getInstance(TRANSFORMATION);
        }
        return CIPHER;
    }

    private AESCryptography(String key) {
        KEY = key;
        System.out.println("Init AES Cryptography with " + KEY);
    }

    @Override
    public String decrypt(String encryptedMessageInHex) throws CryptographyException {
        SecretKeySpec secretKeySpec;
        Cipher cipher;
        String decryptedMessage = "";
        try {
            secretKeySpec = getSecretKeySpec();
            cipher = getCipher();
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);

            byte[] encryptedMessagesInBytes = Hex.decodeHex(encryptedMessageInHex.toCharArray());
            byte[] decryptedMessageInBytes = cipher.doFinal(encryptedMessagesInBytes);

            decryptedMessage = new String(decryptedMessageInBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new CryptographyException("There is no algorithm: " + ALGORITHM_NAME + ". ", e);
        } catch (NoSuchPaddingException e) {
            throw new CryptographyException("There is no padding: " + ALGORITHM_PADDING + ". ", e);
        } catch (InvalidKeyException e) {
            throw new CryptographyException("Please check the key. ", e);
        } catch (IllegalBlockSizeException e) {
            throw new CryptographyException("The length of message \"" + encryptedMessageInHex + "\" is incorrect. ", e);
        } catch (BadPaddingException e) {
            throw new CryptographyException("The message \"" + encryptedMessageInHex + "\" is not padded properly. ", e);
        } catch (DecoderException e) {
            e.printStackTrace();
        }

        return decryptedMessage;
    }

    @Override
    public String encrypt(String message) throws CryptographyException {
        SecretKeySpec secretKeySpec;
        Cipher cipher;
        String encryptedMessageInHex = "";

        try {
            secretKeySpec = getSecretKeySpec();
            cipher = getCipher();
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);

            byte[] messagesInBytes = message.getBytes("UTF-8");
            byte[] encryptedMessageInBytes = cipher.doFinal(messagesInBytes);

            encryptedMessageInHex = Hex.encodeHexString(encryptedMessageInBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new CryptographyException("There is no algorithm: " + ALGORITHM_NAME + ". ", e);
        } catch (NoSuchPaddingException e) {
            throw new CryptographyException("There is no padding: " + ALGORITHM_PADDING + ". ", e);
        } catch (InvalidKeyException e) {
            throw new CryptographyException("Please check the key. ", e);
        } catch (IllegalBlockSizeException e) {
            throw new CryptographyException("The length of message \"" + message + "\" is incorrect. ", e);
        } catch (BadPaddingException e) {
            throw new CryptographyException("The message \"" + message + "\" is not padded properly. ", e);
        } catch (UnsupportedEncodingException e) {
            throw new CryptographyException("UTF-8 Encoding is unsupported. ", e);
        } catch (DecoderException e) {
            e.printStackTrace();
        }

        return encryptedMessageInHex;
    }
}