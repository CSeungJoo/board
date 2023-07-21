package kr.pah.pcs.board.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LoginDto {
    private String username;
    private String password;

    public LoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
