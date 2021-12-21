package me.jinmin.boardver2.comment.service;

import lombok.RequiredArgsConstructor;
import me.jinmin.boardver2.comment.model.Comment;
import me.jinmin.boardver2.comment.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentDeleteService {

    private final CommentFindService commentFindService;
    private final CommentRepository commentRepository;

    @Transactional
    public void deleteComment(Long userId, Long boardId) {
        Comment comment = commentFindService.findCommentByUserAndBoard(userId, boardId);
        commentRepository.deleteById(comment.getComment_id());
    }
}
