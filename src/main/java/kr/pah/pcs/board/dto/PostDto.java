package kr.pah.pcs.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class PostDto {
    private String title;
    private String content;
    private String username;
    private int view;
    private String createdDate;

    @QueryProjection
    public PostDto(String title, String content, String username, int view, String createdDate) {
        this.title = title;
        this.content = content;
        this.username = username;
        this.view = view;
        this.createdDate = createdDate;
    }
}
