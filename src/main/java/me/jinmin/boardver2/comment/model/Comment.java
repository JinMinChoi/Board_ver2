package me.jinmin.boardver2.comment.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jinmin.boardver2.board.model.Board;
import me.jinmin.boardver2.user.model.User;
import me.jinmin.boardver2.util.TimeEntity;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long comment_id;

    @Column(name = "comment_content")
    private String content;

    @Column(name = "comment_writer")
    private String writer;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Comment(String content, String writer, Board board) {
        this.content = content;
        this.writer = writer;
        writtenBoard(board);
    }

    private void writtenBoard(Board board) {
        this.board = board;
        board.getComments().add(this);
    }

    public void writtenByUser(User user) {
        this.user = user;
    }

    public Long update(String content) {
        this.content = content;
        return this.getComment_id();
    }
}
