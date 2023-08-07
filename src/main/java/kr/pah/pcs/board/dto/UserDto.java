package kr.pah.pcs.board.dto;

import kr.pah.pcs.board.domain.Users;
import lombok.Data;
import lombok.Getter;

public class UserDto {

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
    public static class LoginDto {
        private String username;
        private String password;

        public LoginDto(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }

    @Data
    @Getter
    public static class ModifiedUserDto {
        private String username;
        private String password;
        private String email;

        public ModifiedUserDto(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;
        }
    }

    @Data
    @Getter
    public static class SignUserDto {

        private String username;
        private String password;
        private String email;

        public SignUserDto(String username, String password, String email) {
            this.username = username;
            this.password = password;
            this.email = email;
        }
    }


}
