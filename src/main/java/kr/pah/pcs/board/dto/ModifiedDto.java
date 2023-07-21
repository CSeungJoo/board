package kr.pah.pcs.board.dto;

import kr.pah.pcs.board.domain.Users;
import lombok.Data;

@Data
public class ModifiedDto {
    private Long id;
    private String title;
    private String content;
    private Users users;

    public ModifiedDto(Long id, String title, String content, Users users) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.users = users;
    }
}
