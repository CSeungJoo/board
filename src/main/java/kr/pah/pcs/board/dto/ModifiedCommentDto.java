package kr.pah.pcs.board.dto;

import kr.pah.pcs.board.domain.Users;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ModifiedCommentDto {
    private Long id;
    private String comment;

    private Users users;

    public ModifiedCommentDto(Long id, String comment, Users users) {
        this.id = id;
        this.comment = comment;
        this.users = users;
    }
}
