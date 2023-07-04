package kr.pah.pcs.board.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.pah.pcs.board.domain.Posts;
import kr.pah.pcs.board.domain.Role;
import kr.pah.pcs.board.domain.Users;
import kr.pah.pcs.board.dto.CreatePostDto;
import kr.pah.pcs.board.exception.CustomException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PostsServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    PostsService postsService;

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
    public void creatPost() throws Exception {
        Users user1 = new Users(1L, "name", "mail", Role.USER);
        CreatePostDto data = new CreatePostDto("title", "content", user1);
        String s = postsService.writePost(data);
        assertThat(s).isEqualTo("ok");
    }

    @Test
    public void creatPostException () throws Exception {
        Users user1 = new Users(null, "name", "mail", Role.USER);
        CreatePostDto data = new CreatePostDto("title", "content", user1);
        assertThatThrownBy(() -> postsService.writePost(data))
                .isInstanceOf(CustomException.class);
    }

}