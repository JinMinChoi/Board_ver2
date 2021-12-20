package me.jinmin.boardver2.comment.service;

import lombok.RequiredArgsConstructor;
import me.jinmin.boardver2.board.model.Board;
import me.jinmin.boardver2.board.service.BoardFindService;
import me.jinmin.boardver2.comment.exception.NotFoundCommentException;
import me.jinmin.boardver2.comment.model.Comment;
import me.jinmin.boardver2.comment.repository.CommentRepository;
import me.jinmin.boardver2.user.model.User;
import me.jinmin.boardver2.user.service.UserFindService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentFindService {

    private final CommentRepository commentRepository;
    private final BoardFindService boardFindService;
    private final UserFindService userFindService;

    @Transactional(readOnly = true)
    public List<Comment> findAllCommentsInBoard(Long boardId) {
        Board board = boardFindService.findById(boardId);
        return commentRepository.findByBoard(board);
    }

    @Transactional(readOnly = true)
    public Comment findCommentByUserAndBoard(Long userId, Long boardId) {
        User user = userFindService.findById(userId);
        Board board = boardFindService.findById(boardId);
        return commentRepository.findByUserAndBoard(user, board)
                .orElseThrow(() -> new NotFoundCommentException(String.format("There is no comment")));
    }
}
