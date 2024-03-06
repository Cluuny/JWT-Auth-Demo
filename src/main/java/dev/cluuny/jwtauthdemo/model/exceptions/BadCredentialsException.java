package dev.cluuny.jwtauthdemo.model.exceptions;

public class BadCredentialsException extends Exception {
    public BadCredentialsException() {
        super("Bad Credentials");
    }
}
