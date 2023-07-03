package kr.pah.pcs.board.domain;


import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Posts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private int view;

    public void postsModified(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Posts(String title, String content, String username, int view) {
        this.title = title;
        this.content = content;
        this.username = username;
        this.view = view;
    }
}
