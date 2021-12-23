package me.jinmin.boardver2.user.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.jinmin.boardver2.board.model.Board;
import me.jinmin.boardver2.comment.model.Comment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "device_token")
    private String deviceToken;

    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public User(String email, String password, String name, UserRole userRole) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.userRole = userRole;
    }

    public User updateUserInfo(String name, String password) {
        this.name = name;
        this.password = password;
        return this;
    }

    public void writeBoard(Board board) {
        this.boards.add(board);
        board.createdByUser(this);
    }

    public void writeComment(Comment comment) {
        this.comments.add(comment);
        comment.writeUser(this);
    }

    public void modifyDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
