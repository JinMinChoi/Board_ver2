package me.jinmin.boardver2.board.service;

import lombok.RequiredArgsConstructor;
import me.jinmin.boardver2.board.api.request.BoardWriteRequest;
import me.jinmin.boardver2.board.model.Board;
import me.jinmin.boardver2.board.repository.BoardRepository;
import me.jinmin.boardver2.user.model.User;
import me.jinmin.boardver2.user.service.UserFindService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardWriteService {

    private final UserFindService userFindService;
    private final BoardRepository boardRepository;

    @Transactional
    public Long writeBoard(Long userId, BoardWriteRequest boardWriteRequest) {
        User user = userFindService.findById(userId);
        Board board = Board.builder()
                .title(boardWriteRequest.getTitle())
                .writer(user.getName())
                .content(boardWriteRequest.getContent())
                .category(boardWriteRequest.getCategory())
                .build();
        Board savedBoard = boardRepository.save(board);
        user.writeBoard(savedBoard);
        return savedBoard.getId();
    }
}
