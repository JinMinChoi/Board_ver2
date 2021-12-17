package me.jinmin.boardver2.comment.service;

import lombok.RequiredArgsConstructor;
import me.jinmin.boardver2.board.model.Board;
import me.jinmin.boardver2.board.service.BoardFindService;
import me.jinmin.boardver2.comment.api.request.CommentWriteRequest;
import me.jinmin.boardver2.comment.model.Comment;
import me.jinmin.boardver2.comment.repository.CommentRepository;
import me.jinmin.boardver2.user.model.User;
import me.jinmin.boardver2.user.service.UserFindService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentWriteService {

    private final CommentRepository commentRepository;
    private final UserFindService userFindService;
    private final BoardFindService boardFindService;

    @Transactional
    public Long writeComment(Long userId, Long boardId, CommentWriteRequest commentWriteRequest) {
        Board board = boardFindService.findById(boardId);
        User user = userFindService.findById(userId);

        Comment comment = Comment.builder()
                .content(commentWriteRequest.getContent())
                .writer(user.getName())
                .board(board)
                .build();

        Comment savedComment = commentRepository.save(comment);
        user.writeComment(savedComment);
        return savedComment.getComment_id();
    }
}
