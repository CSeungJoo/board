package kr.pah.pcs.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.pah.pcs.board.domain.Comment;
import kr.pah.pcs.board.domain.QComment;
import kr.pah.pcs.board.domain.QUsers;
import kr.pah.pcs.board.dto.GetCommentDto;
import kr.pah.pcs.board.dto.QGetCommentDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static kr.pah.pcs.board.domain.QComment.comment1;
import static kr.pah.pcs.board.domain.QUsers.users;

@Repository
public class CommentQuerydslRepository {

    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public CommentQuerydslRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<GetCommentDto> findAll(Pageable pageable) {
        return queryFactory
                .select(new QGetCommentDto(
                        comment1.id,
                        comment1.comment,
                        comment1.users.username,
                        comment1.users.role,
                        comment1.modifiedDate
                ))
                .offset(0)
                .limit(10)
                .from(comment1)
                .join(comment1.users, users)
                .fetch();
    }

}
