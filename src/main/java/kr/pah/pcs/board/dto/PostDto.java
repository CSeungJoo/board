package kr.pah.pcs.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import kr.pah.pcs.board.domain.Users;
import lombok.Data;
import lombok.Getter;

public class PostDto {

    @Data
    @Getter
    public static class CreatePostDto {
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

    @Data
    @Getter
    public static class DeleteDto {
        private Long id;
        private Users users;

        public DeleteDto(Long id, Users users) {
            this.id = id;
            this.users = users;
        }
    }

    @Data
    @Getter
    public static class GetPostsDto {
        private Long id;
        private String title;
        private String username;
        private int view;
        private String createdDate;

        @QueryProjection
        public GetPostsDto(Long id, String title, String username, int view, String createdDate) {
            this.id = id;
            this.title = title;
            this.username = username;
            this.view = view;
            this.createdDate = createdDate;
        }
    }
    @Data
    @Getter
    public static class GetPostDto {

    private String title;
    private String content;
    private String username;
    private int view;
    private String createdDate;

    @QueryProjection
    public GetPostDto(String title, String content, String username, int view, String createdDate) {
        this.title = title;
        this.content = content;
        this.username = username;
        this.view = view;
        this.createdDate = createdDate;
        }
    }

    @Data
    @Getter
    public static class ModifiedPostDto {
        private Long id;
        private String title;
        private String content;
        private Users users;

        public ModifiedPostDto(Long id, String title, String content, Users users) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.users = users;
        }
    }
}
