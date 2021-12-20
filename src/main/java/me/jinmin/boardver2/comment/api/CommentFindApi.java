package me.jinmin.boardver2.comment.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jinmin.boardver2.comment.model.Comment;
import me.jinmin.boardver2.comment.service.CommentFindService;
import me.jinmin.boardver2.util.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentFindApi {

    private final CommentFindService commentFindService;

    @GetMapping("/{boardId}")
    public ApiResult<List<Comment>> findComments(@PathVariable Long boardId) {
        try {
            return ApiResult.succeed(commentFindService.findAllCommentsInBoard(boardId));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResult.failed(e.getMessage());
        }
    }
}
