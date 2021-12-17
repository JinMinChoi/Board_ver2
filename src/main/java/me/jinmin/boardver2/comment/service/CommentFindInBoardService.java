package me.jinmin.boardver2.comment.service;

import lombok.RequiredArgsConstructor;
import me.jinmin.boardver2.board.model.Board;
import me.jinmin.boardver2.board.service.BoardFindService;
import me.jinmin.boardver2.comment.model.Comment;
import me.jinmin.boardver2.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentFindInBoardService {

    private final CommentRepository commentRepository;
    private final BoardFindService boardFindService;

    @Transactional(readOnly = true)
    public List<Comment> findCommentInBoard(Long boardId) {
        Board board = boardFindService.findById(boardId);
        return commentRepository.findByBoard(board);
    }
}
