package me.jinmin.boardver2.comment.api;

import lombok.RequiredArgsConstructor;
import me.jinmin.boardver2.comment.service.CommentDeleteService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/comments/delete")
@RestController
@RequiredArgsConstructor
public class CommentDeleteApi {
    private final CommentDeleteService commentDeleteService;

    @DeleteMapping("/{userId}/{boardId}")
    public void deleteComment(@PathVariable Long userId, @PathVariable Long boardId) {
        commentDeleteService.deleteComment(userId, boardId);
    }
}
