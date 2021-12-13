package me.jinmin.boardver2.board.exception;

public class NotFoundBoardException extends RuntimeException {
    public NotFoundBoardException(String msg) {
        super(msg);
    }

    public NotFoundBoardException(String msg, Throwable throwable) {
        super(msg, throwable);
    }
}
