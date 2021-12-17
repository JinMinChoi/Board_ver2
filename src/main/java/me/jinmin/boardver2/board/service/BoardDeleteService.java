package me.jinmin.boardver2.board.service;

import lombok.RequiredArgsConstructor;
import me.jinmin.boardver2.board.exception.NotHavePermissionBoardException;
import me.jinmin.boardver2.board.model.Board;
import me.jinmin.boardver2.board.repository.BoardRepository;
import me.jinmin.boardver2.user.model.User;
import me.jinmin.boardver2.user.service.UserFindService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class BoardDeleteService {

    private final UserFindService userFindService;
    private final BoardFindService boardFindService;
    private final BoardRepository boardRepository;

    @Transactional
    public void deleteBoardById(Long userId, Long boardId) {
        User user = userFindService.findById(userId);
        Board board = boardFindService.findById(boardId);
        checkBoardLoginUser(user, board);
        boardRepository.deleteById(boardId);
    }

    private void checkBoardLoginUser(User user, Board board) {
        if (!Objects.equals(board.getUser().getUser_id(), user.getUser_id())) {
            throw new NotHavePermissionBoardException("해당 게시물을 삭제할 권한이 없습니다.");
        }
    }
}
