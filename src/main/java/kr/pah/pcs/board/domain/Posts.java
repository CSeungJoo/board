package kr.pah.pcs.board.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Posts extends DefaultTime{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "posts_id")
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @Column(nullable = false)
    private int view;

    @JsonIgnore
    @OneToMany(mappedBy = "posts")
    private List<Comment> comment;

    public void postsModified(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Posts(String title, String content, Users users, int view) {
        this.title = title;
        this.content = content;
        this.users = users;
        this.view = view;
    }

    public Posts(String title, String content, Users users) {
        this.title = title;
        this.content = content;
        this.users = users;
    }

    public void modified(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
