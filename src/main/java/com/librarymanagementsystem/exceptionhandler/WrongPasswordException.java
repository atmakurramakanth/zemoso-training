package com.librarymanagementsystem.exceptionhandler;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException(String msg,Throwable cause){
        super(msg,cause);
    }

    public WrongPasswordException(String msg){
        super(msg);
    }

    public WrongPasswordException(Throwable cause){
        super(cause);
    }
}
