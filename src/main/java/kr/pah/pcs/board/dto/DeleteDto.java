package kr.pah.pcs.board.dto;

import com.querydsl.core.annotations.QueryProjection;
import kr.pah.pcs.board.domain.Users;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class DeleteDto {
    private Long id;
    private Users users;

    public DeleteDto(Long id, Users users) {
        this.id = id;
        this.users = users;
    }
}
