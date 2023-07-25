package kr.pah.pcs.board.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ModifiedUserDto {
    private String username;
    private String password;
    private String email;

    public ModifiedUserDto(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}