package me.jinmin.boardver2.board.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.jinmin.boardver2.board.service.BoardDeleteService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/boards/delete")
@RequiredArgsConstructor
public class BoardDeleteApi {

    private final BoardDeleteService boardDeleteService;

    @DeleteMapping("/{userId}/{boardId}")
    public void deleteBoardById(@PathVariable Long userId,
                                @PathVariable Long boardId) {
        boardDeleteService.deleteBoardById(userId, boardId);
    }
}
