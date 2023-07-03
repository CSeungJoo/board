package kr.pah.pcs.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter @Setter
@Builder
public class PostsDto {

    private Long id;
    private String title;
    private String username;
    private int view;

    @QueryProjection
    public PostsDto(Long id, String title, String username, int view) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.view = view;
    }
}
