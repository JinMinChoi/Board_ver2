package me.jinmin.boardver2.user.exception;

public class UnMatchedPasswordException extends RuntimeException {
    public UnMatchedPasswordException(String msg) {
        super(msg);
    }

    public UnMatchedPasswordException(String msg, Throwable t) {
        super(msg, t);
    }
}
