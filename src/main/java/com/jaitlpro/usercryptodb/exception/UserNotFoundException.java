package com.jaitlpro.usercryptodb.exception;

/**
 * Created by Igor on 18.12.2014.
 */
public class UserNotFoundException extends Exception {
    public UserNotFoundException(String s) {
        super(s);
    }

    public UserNotFoundException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
