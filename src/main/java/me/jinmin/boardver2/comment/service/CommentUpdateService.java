package me.jinmin.boardver2.comment.service;

import lombok.RequiredArgsConstructor;
import me.jinmin.boardver2.comment.api.request.CommentUpdateRequest;
import me.jinmin.boardver2.comment.model.Comment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentUpdateService {

    private final CommentFindService commentFindService;

    @Transactional
    public Long updateComment(Long userId, Long boardId, CommentUpdateRequest commentUpdateRequest) {
        Comment comment = commentFindService.findCommentByUserAndBoard(userId, boardId);
        return comment.update(
                commentUpdateRequest.getContent()
        );
    }
}
