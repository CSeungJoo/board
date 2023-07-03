package kr.pah.pcs.board.controller;

import kr.pah.pcs.board.domain.Posts;
import kr.pah.pcs.board.dto.PostsDto;
import kr.pah.pcs.board.repository.PostsQuerydslRepository;
import kr.pah.pcs.board.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostsRepository postsRepository;

    @Autowired
    PostsQuerydslRepository postsQuerydslRepository;

    @GetMapping("/post")
    public List<PostsDto> getPosts() {
        return postsQuerydslRepository.findAll();
    }
}
