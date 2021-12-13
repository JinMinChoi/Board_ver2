package me.jinmin.boardver2.board.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jinmin.boardver2.board.api.request.BoardWriteRequest;
import me.jinmin.boardver2.board.service.BoardWriteService;
import me.jinmin.boardver2.util.ApiResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardWriteApi {

    private final BoardWriteService boardWriteService;

    @PostMapping("/{userId}/write")
    public ApiResult<Long> writeBoard(@PathVariable Long userId,
                                      @RequestBody BoardWriteRequest boardWriteRequest) {
        try {
            Long boardId = boardWriteService.writeBoard(userId, boardWriteRequest);
            return ApiResult.succeed(boardId);
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResult.failed(e.getMessage());
        }
    }
}
