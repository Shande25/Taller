package com.taller.Taller.Exceptions;

public class MiException extends Exception{

    public MiException(String message) {
        super(message);
    }

    public MiException(String message, Throwable cause) {
        super(message, cause);
    }
}
