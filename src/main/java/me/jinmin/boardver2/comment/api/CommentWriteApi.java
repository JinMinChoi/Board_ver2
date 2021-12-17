package me.jinmin.boardver2.comment.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jinmin.boardver2.comment.api.request.CommentWriteRequest;
import me.jinmin.boardver2.comment.service.CommentWriteService;
import me.jinmin.boardver2.util.ApiResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor
public class CommentWriteApi {

    private final CommentWriteService commentWriteService;

    @PostMapping("write/{userId}/{boardId}")
    public ApiResult<Long> writeComment(@PathVariable Long userId,
                                        @PathVariable Long boardId,
                                        @RequestBody CommentWriteRequest commentWriteRequest) {
        try {
            return ApiResult.succeed(commentWriteService.writeComment(userId, boardId, commentWriteRequest));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResult.failed(e.getMessage());
        }
    }

}
