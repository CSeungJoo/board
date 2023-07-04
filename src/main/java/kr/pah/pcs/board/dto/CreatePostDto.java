package kr.pah.pcs.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import kr.pah.pcs.board.domain.Users;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreatePostDto {

    private String title;
    private String content;
    private Users users;

    @QueryProjection
    public CreatePostDto(String title, String content, Users users) {
        this.title = title;
        this.content = content;
        this.users = users;
    }
}
