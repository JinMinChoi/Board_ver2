package me.jinmin.boardver2.user.exception;

public class DuplicatedEmailException extends RuntimeException {
    public DuplicatedEmailException(String msg) {
        super(msg);
    }

    public DuplicatedEmailException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
