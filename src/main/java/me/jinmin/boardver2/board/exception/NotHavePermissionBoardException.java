package me.jinmin.boardver2.board.exception;

public class NotHavePermissionBoardException extends RuntimeException {
    public NotHavePermissionBoardException(String message) {
        super(message);
    }
}
