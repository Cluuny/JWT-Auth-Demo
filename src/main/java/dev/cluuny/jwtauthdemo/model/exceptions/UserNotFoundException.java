package dev.cluuny.jwtauthdemo.model.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException() {
        super("User Not Found!");
    }
}
