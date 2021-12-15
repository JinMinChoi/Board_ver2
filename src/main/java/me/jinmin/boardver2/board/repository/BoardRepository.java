package me.jinmin.boardver2.board.repository;

import me.jinmin.boardver2.board.model.Board;
import me.jinmin.boardver2.board.model.BoardCategory;
import me.jinmin.boardver2.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByCategory(BoardCategory category);

    List<Board> findByUser(User user);
}
