package kr.pah.pcs.board.dto;

import kr.pah.pcs.board.domain.Posts;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class WriteCommentDto {
    private String comment;
    private Long post_id;

    public WriteCommentDto(String comment, Long post_id) {
        this.comment = comment;
        this.post_id = post_id;
    }
}
