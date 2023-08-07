package kr.pah.pcs.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import kr.pah.pcs.board.domain.Users;
import lombok.Data;
import lombok.Getter;

public class CommentDto {

    @Data
    @Getter
    public static class GetCommentDto {
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
    @Data
    @Getter
    public static class ModifiedCommentDto {
        private Long id;
        private String comment;
        private Users users;

        public ModifiedCommentDto(Long id, String comment, Users users) {
            this.id = id;
            this.comment = comment;
            this.users = users;
        }
    }

    @Data
    @Getter
    public static class WriteCommentDto {
        private String comment;
        private Long post_id;

        public WriteCommentDto(String comment, Long post_id) {
            this.comment = comment;
            this.post_id = post_id;
        }
    }


}
