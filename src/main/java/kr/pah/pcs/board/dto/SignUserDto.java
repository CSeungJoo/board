package kr.pah.pcs.board.dto;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class SignUserDto {

    private String username;
    private String email;
}
