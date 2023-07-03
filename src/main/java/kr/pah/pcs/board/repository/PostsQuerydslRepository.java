package kr.pah.pcs.board.repository;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.pah.pcs.board.dto.PostDto;
import kr.pah.pcs.board.dto.PostsDto;
import kr.pah.pcs.board.dto.QPostDto;
import kr.pah.pcs.board.dto.QPostsDto;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


import java.util.List;

import static kr.pah.pcs.board.domain.QPosts.posts;
import static kr.pah.pcs.board.domain.QUsers.*;


@Repository
public class PostsQuerydslRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public PostsQuerydslRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }


    /**
     * DTO를 통하여 조회하기
     * @return
     */
    public List<PostsDto> findAll() {
        return queryFactory
                .select(new QPostsDto(
                        posts.id.as("posts_id"),
                        posts.title,
                        users.username,
                        posts.view
                ))
                .from(posts)
                .join(posts.users, users)
                .fetch();
    }

    /**
     *
     * DTO를 통하여 페이징 조회
     * @return
     */
    public List<PostsDto> findAll(Pageable pageable) {
        return queryFactory
                .select(new QPostsDto(
                        posts.id.as("posts_id"),
                        posts.title,
                        users.username,
                        posts.view
                ))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .from(posts)
                .join(posts.users, users)
                .fetch();
    }

    public PostDto findPostById(Long id) {
        return queryFactory
                .select(new QPostDto(
                        posts.title,
                        posts.content,
                        users.username,
                        posts.view,
                        posts.createdDate
                ))
                .from(posts)
                .join(posts.users, users)
                .where(posts.id.eq(id))
                .fetchOne();
    }
}
