package com.southsystem.portobelloaccount.exception;

public class AccountException extends Exception {

    private static final long serialVersionUID = 3028883219789015806L;

    public AccountException(String message) {
        super(message);
    }

    public AccountException(Throwable throwable) {
    }
}
