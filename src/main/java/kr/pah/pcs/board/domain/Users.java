package kr.pah.pcs.board.domain;

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
    @Column(length = 30)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "users")
    private List<Posts> posts;

    public void changeName(String username) {
        this.username = username;
    }

    public Users(Long id, String username, String email, Role role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
