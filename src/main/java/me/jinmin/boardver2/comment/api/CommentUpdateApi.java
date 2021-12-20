package me.jinmin.boardver2.comment.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jinmin.boardver2.comment.api.request.CommentUpdateRequest;
import me.jinmin.boardver2.comment.service.CommentUpdateService;
import me.jinmin.boardver2.util.ApiResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/comments/update")
@RequiredArgsConstructor
public class CommentUpdateApi {

    private final CommentUpdateService commentUpdateService;

    @PutMapping("/{userId}/{boardId}")
    public ApiResult<Long> updateComment(@PathVariable Long userId,
                                         @PathVariable Long boardId,
                                         @RequestBody CommentUpdateRequest commentUpdateRequest) {
        try {
            return ApiResult.succeed(commentUpdateService.updateComment(userId, boardId, commentUpdateRequest));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResult.failed(e.getMessage());
        }
    }
}
