package br.com.phricardo.schedulingtechnicians.exception;

public class LoginException extends RuntimeException {
    public LoginException(String message) {
        super(message);
    }

    public LoginException() {
        super("Login does not exist or password is invalid");
    }
}

