package br.com.jacto.authservice.exceptions;


import java.io.Serial;

public class AuthException extends Exception {
    @Serial
    private static final long serialVersionUID = 1000380839289383166L;
    public AuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public AuthException(String msg) {
        super(msg);
    }
}
