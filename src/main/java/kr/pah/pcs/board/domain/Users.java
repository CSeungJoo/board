package kr.pah.pcs.board.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Users extends DefaultTime{

    @Id @GeneratedValue
    @Column(name = "users_id")
    private Long id;
    @Column(length = 13)
    private String username;

    private String password;
    @Column(length = 30)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "users")
    private List<Posts> posts;

    @OneToMany(mappedBy = "users")
    private List<Comment> comments;

    public void modified(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;

    }

    public Users(Long id, String username, String password, String email, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public Users(Long id, String username, String email, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
