package kr.pah.pcs.board.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.pah.pcs.board.domain.Role;
import kr.pah.pcs.board.domain.Users;
import kr.pah.pcs.board.repository.PostsQuerydslRepository;
import kr.pah.pcs.board.repository.PostsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PostsServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    PostsService postsService;

    @Autowired
    PostsQuerydslRepository postsQuerydslRepository;
    @Autowired
    PostsRepository postsRepository;

    @Test
    public void creatPost() throws Exception {
        Users user1 = new Users(1L, "name", "mail", Role.USER);
        CreatePostDto data = new CreatePostDto("title", "content", user1);
        String s = postsService.writePost(data);
        assertThat(s).isEqualTo("ok");
    }

//    @Test
//    public void creatPostException () throws Exception {
//        Users user1 = new Users(null, "name", "mail", Role.USER);
//        CreatePostDto data = new CreatePostDto("title", "content", user1);
//        assertThatThrownBy(() -> postsService.writePost(data))
//                .isInstanceOf(CustomException.class);
//    }
//
//    @Test
//    public void deletePost() throws Exception {
//        Users user = new Users(552L, "name", "mail", Role.USER);
//        DeleteDto deleteDto = new DeleteDto(136L, user);
//        assertThatThrownBy(() -> postsService.deletePost(deleteDto))
//                .isInstanceOf(CustomException.class);
//    }
//
//    @Test
//    public void modified() throws Exception {
//        Users user = new Users(102L, "name", "mail@mail", Role.USER);
//        ModifiedDto modifiedDto = new ModifiedDto(44L, "newTitle", "newContent", user);
//        postsService.modified(modifiedDto);
//    }

}