package me.jinmin.boardver2.board.service;

import lombok.RequiredArgsConstructor;
import me.jinmin.boardver2.board.api.request.BoardUpdateRequest;
import me.jinmin.boardver2.board.exception.NotHavePermissionBoardEditException;
import me.jinmin.boardver2.board.model.Board;
import me.jinmin.boardver2.user.model.User;
import me.jinmin.boardver2.user.service.UserFindService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BoardUpdateService {
    private final UserFindService userFindService;
    private final BoardFindService boardFindService;

    @Transactional
    public Long updateBoard(Long userId, Long boardId, BoardUpdateRequest boardUpdateRequest) {
        User user = userFindService.findById(userId);
        Board board = boardFindService.findById(boardId);
        checkBoardLoginUser(user, board);
        Long updatedBoardId = board.update(
                boardUpdateRequest.getTitle(),
                boardUpdateRequest.getContent(),
                boardUpdateRequest.getCategory()
        );

        return updatedBoardId;
    }

    private void checkBoardLoginUser(User user, Board board) {
        if (!Objects.equals(board.getUser().getId(), user.getId())) {
            throw new NotHavePermissionBoardEditException(String.format("%s를 수정할 권한이 없습니다.", board.getTitle()));
        }
    }
}
