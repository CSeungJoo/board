package kr.pah.pcs.board.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.pah.pcs.board.domain.Posts;
import kr.pah.pcs.board.dto.PostsDto;
import org.aspectj.lang.annotation.Before;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostsQuerydslRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    PostsQuerydslRepository postsQuerydslRepository;

    @BeforeEach
    public void before() {
        Posts posts1 = new Posts(null ,"1" ,"1", "1", 0);
        Posts posts2 = new Posts(null ,"2" ,"2", "2", 0);
        Posts posts3 = new Posts(null ,"3" ,"3", "3", 0);
        Posts posts4 = new Posts(null ,"4" ,"4", "4", 0);

        em.persist(posts1);
        em.persist(posts2);
        em.persist(posts3);
        em.persist(posts4);
    }

    @Test
    public void getPosts() throws Exception {
        List<PostsDto> result = postsQuerydslRepository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(4);
    }
}