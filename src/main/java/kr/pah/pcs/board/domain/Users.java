package kr.pah.pcs.board.domain;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Users {

    @Id @GeneratedValue
    private Long id;
    @Column(length = 13)
    private String username;
    @Column(length = 30)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    public void changeName(String username) {
        this.username = username;
    }
}
