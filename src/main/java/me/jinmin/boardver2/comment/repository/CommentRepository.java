package me.jinmin.boardver2.comment.repository;

import me.jinmin.boardver2.board.model.Board;
import me.jinmin.boardver2.comment.model.Comment;
import me.jinmin.boardver2.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findByBoard(Board board);

    Optional<Comment> findByUserAndBoard(User user, Board board);
}
