package kr.pah.pcs.board.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {

    INVALID_DELETE_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    INVALID_LOGIN_DATA(HttpStatus.NOT_FOUND, "존재하지 않는 계정입니다."),
    OVERLAP_POST_DATA(HttpStatus.BAD_REQUEST, "이미 존재하는 게시글입니다."),
    INVALID_POST_DATA(HttpStatus.BAD_REQUEST, "정보에 잘못이 있거나 손상되었습니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "해당하는 정보의 게시글을 찾을수 없습니다.");

    private final HttpStatus httpStatus;
    private final String message;
}
