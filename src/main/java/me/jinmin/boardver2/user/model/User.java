package me.jinmin.boardver2.user.model;

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
    private Long id;

    @Column(name = "email")
    private String email;
    private String password;
    private String name;

    @Enumerated(value = EnumType.STRING)
    private UserRole userRole;

    @OneToMany(mappedBy = "user")
    private List<Board> boards = new ArrayList<>();

    @OneToMany(mappedBy = "user")
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
}
