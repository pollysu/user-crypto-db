package com.jaitlpro.usercryptodb.exception;

/**
 * Created by Igor on 19.12.2014.
 */
public class FieldNotFilledException extends Exception {
    public FieldNotFilledException(String s) {
        super(s);
    }

    public FieldNotFilledException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
