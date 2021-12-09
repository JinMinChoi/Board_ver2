package me.jinmin.boardver2.user.exception;

public class NotFoundUserException extends RuntimeException {
    public NotFoundUserException(String msg) {
        super(msg);
    }

    public NotFoundUserException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
