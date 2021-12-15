package me.jinmin.boardver2.board.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jinmin.boardver2.comment.model.Comment;
import me.jinmin.boardver2.user.model.User;
import me.jinmin.boardver2.util.TimeEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "board_content")
    private String content;

    @Column(name = "writer")
    private String writer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @Enumerated(EnumType.STRING)
    private BoardCategory category;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @Builder
    public Board(String title, String writer, String content, BoardCategory category) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.category = category;
    }

    public void createdByUser(User user) {
        this.user = user;
    }

    public Long update(String title, String content, BoardCategory category) {
        this.title = title;
        this.content = content;
        this.category = category;
        return this.getId();
    }
}
