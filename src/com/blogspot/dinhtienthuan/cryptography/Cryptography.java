package com.blogspot.dinhtienthuan.cryptography;

import java.io.Serializable;

import com.blogspot.dinhtienthuan.exception.CryptographyException;

public interface Cryptography extends Serializable {
    public String decrypt(String encryptedMessages) throws CryptographyException;

    public String encrypt(String message) throws CryptographyException;
}
