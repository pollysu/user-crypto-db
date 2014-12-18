package com.jaitlpro.usercryptodb.exception;

/**
 * Created by Igor on 18.12.2014.
 */
public class UserIsExistException extends Exception {
    public UserIsExistException(String s) {
        super(s);
    }

    public UserIsExistException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
