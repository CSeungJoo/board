package kr.pah.pcs.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import kr.pah.pcs.board.domain.Users;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class GetCommentDto {
    private Long id;
    private String content;
    private String username;
    private Enum role;
    private String modifiedDate;

    @QueryProjection
    public GetCommentDto(Long id, String content, String username, Enum role, String modifiedDate) {
        this.id = id;
        this.content = content;
        this.username = username;
        this.role = role;
        this.modifiedDate = modifiedDate;
    }
}
