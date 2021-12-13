package me.jinmin.boardver2.board.service;

import lombok.RequiredArgsConstructor;
import me.jinmin.boardver2.board.exception.NotFoundBoardException;
import me.jinmin.boardver2.board.model.Board;
import me.jinmin.boardver2.board.model.BoardCategory;
import me.jinmin.boardver2.board.repository.BoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardFindService {

    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Long findById(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new NotFoundBoardException(String.format("Board is not Found!")));
        return board.getId();
    }

    @Transactional(readOnly = true)
    public List<Board> findAll() {
        return boardRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Board> findByCategory(BoardCategory category) {
        return boardRepository.findByCategory(category);
    }
}
