package kr.pah.pcs.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.pah.pcs.board.domain.Comment;
import kr.pah.pcs.board.domain.QComment;
import kr.pah.pcs.board.domain.QUsers;
import kr.pah.pcs.board.dto.GetCommentDto;
import kr.pah.pcs.board.dto.QGetCommentDto;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
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

    public List<GetCommentDto> findAllByPost(Long id ,Pageable pageable) {
        return queryFactory
                .select(new QGetCommentDto(
                        comment1.id,
                        comment1.comment,
                        comment1.users.username,
                        comment1.users.role,
                        comment1.modifiedDate
                ))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .from(comment1)
                .join(comment1.users, users)
                .where(comment1.posts.id.eq(id))
                .fetch();
    }

    public Comment findCommentById(Long id) {
        return queryFactory
                .selectFrom(comment1)
                .where(comment1.id.eq(id))
                .fetchOne();
    }
}
