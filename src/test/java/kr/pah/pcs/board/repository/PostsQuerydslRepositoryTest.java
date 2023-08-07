package kr.pah.pcs.board.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.pah.pcs.board.domain.Posts;
import kr.pah.pcs.board.domain.Role;
import kr.pah.pcs.board.domain.Users;
import kr.pah.pcs.board.dto.PostDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class PostsQuerydslRepositoryTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    PostsQuerydslRepository postsQuerydslRepository;

    @BeforeEach
    public void before() {

        Users users = new Users(null, "name", "mail@mail", Role.USER);

        em.persist(users);

        Posts posts1 = new Posts("1" ,"1", users, 0);
        Posts posts2 = new Posts("2" ,"2", users, 0);
        Posts posts3 = new Posts("3" ,"3", users, 0);
        Posts posts4 = new Posts("4" ,"4", users, 0);
        Posts posts5 = new Posts("5" ,"5", users, 0);
        Posts posts6 = new Posts("6" ,"6", users, 0);
        Posts posts7 = new Posts("7" ,"7", users, 0);
        Posts posts8 = new Posts("8" ,"8", users, 0);
        Posts posts9 = new Posts("9" ,"9", users, 0);
        Posts posts10 = new Posts("10" ,"10", users, 0);
        Posts posts11 = new Posts("11" ,"11", users, 0);

        em.persist(posts1);
        em.persist(posts2);
        em.persist(posts3);
        em.persist(posts4);
        em.persist(posts5);
        em.persist(posts6);
        em.persist(posts7);
        em.persist(posts8);
        em.persist(posts9);
        em.persist(posts10);
        em.persist(posts11);
    }

    @Test
    public void getPosts() throws Exception {
        List<PostsDto> result = postsQuerydslRepository.findAll();

        assertThat(result.size()).isEqualTo(11);
    }
    
    @Test
    public void getPostsByPaging() throws Exception {
        PageRequest pageable = PageRequest.of(0, 10);
        List<PostsDto> result = postsQuerydslRepository.findAll(pageable);

        for (PostsDto postsDto : result) {
            System.out.println("postsDto = " + postsDto.getTitle());
        }
    }
    
    @Test
    public void findPostByIdTest() throws Exception {
        postsQuerydslRepository.findAll();

        PostDto result = postsQuerydslRepository.findPostById(1L);

        assertThat(result.getTitle()).isEqualTo("1");
    }
}