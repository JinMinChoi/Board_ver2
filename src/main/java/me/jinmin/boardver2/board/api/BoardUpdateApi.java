package me.jinmin.boardver2.board.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jinmin.boardver2.board.api.request.BoardUpdateRequest;
import me.jinmin.boardver2.board.service.BoardUpdateService;
import me.jinmin.boardver2.util.ApiResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/boards/update")
@RequiredArgsConstructor
public class BoardUpdateApi {

    private final BoardUpdateService boardUpdateService;

    @PutMapping("/{userId}/{boardId}")
    public ApiResult<Long> updateBoard(@PathVariable Long userId,
                                       @PathVariable Long boardId,
                                       @RequestBody BoardUpdateRequest boardUpdateRequest) {
        try {
            return ApiResult.succeed(boardUpdateService.updateBoard(userId, boardId, boardUpdateRequest));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResult.failed(e.getMessage());
        }
    }
}
