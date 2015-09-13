package com.blogspot.dinhtienthuan.exception;

public class CryptographyException extends RuntimeException {
    private static final long serialVersionUID = -928732155879008877L;

    public CryptographyException(String message) {
        super(message);
    }

    public CryptographyException(String message, Exception exception) {
        super(message, exception);
    }
}