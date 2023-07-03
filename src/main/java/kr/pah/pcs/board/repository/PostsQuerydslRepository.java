package kr.pah.pcs.board.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.pah.pcs.board.dto.PostsDto;
import kr.pah.pcs.board.dto.QPostsDto;
import org.springframework.stereotype.Repository;


import java.util.List;

import static kr.pah.pcs.board.domain.QPosts.posts;


@Repository
public class PostsQuerydslRepository {
    private final EntityManager em;
    private final JPAQueryFactory queryFactory;

    public PostsQuerydslRepository(EntityManager em) {
        this.em = em;
        this.queryFactory = new JPAQueryFactory(em);
    }

    public List<PostsDto> findAll() {
        return queryFactory
                .select(new QPostsDto(
                        posts.id.as("posts_id"),
                        posts.title,
                        posts.username,
                        posts.view
                ))
                .from(posts)
                .fetch();
    }

}
