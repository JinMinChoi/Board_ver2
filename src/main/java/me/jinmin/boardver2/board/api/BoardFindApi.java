package me.jinmin.boardver2.board.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jinmin.boardver2.board.model.Board;
import me.jinmin.boardver2.board.model.BoardCategory;
import me.jinmin.boardver2.board.service.BoardFindService;
import me.jinmin.boardver2.util.ApiResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardFindApi {

    private final BoardFindService boardFindService;

    @GetMapping()
    public ApiResult<List<Board>> findAll() {
        try {
            return ApiResult.succeed(boardFindService.findAll());
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResult.failed(e.getMessage());
        }
    }

    @GetMapping("/{boardId}")
    public ApiResult<Long> findById(@PathVariable Long boardId) {
        try {
            return ApiResult.succeed(boardFindService.findById(boardId));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResult.failed(e.getMessage());
        }
    }

    @GetMapping("/category/{category}")
    public ApiResult<List<Board>> findByCategory(@PathVariable BoardCategory category) {
        try {
            return ApiResult.succeed(boardFindService.findByCategory(category));
        } catch (Exception e) {
            log.error(e.getMessage());
            return ApiResult.failed(e.getMessage());
        }
    }
}
