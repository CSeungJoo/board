package kr.pah.pcs.board.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.pah.pcs.board.domain.Posts;
import kr.pah.pcs.board.dto.*;
import kr.pah.pcs.board.exception.CustomException;
import kr.pah.pcs.board.exception.ErrorCode;
import kr.pah.pcs.board.repository.PostsQuerydslRepository;
import kr.pah.pcs.board.repository.PostsRepository;
import kr.pah.pcs.board.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class PostController {

    private final EntityManager em;

    private final PostsRepository postsRepository;
    private final PostsService postsService;
    private final PostsQuerydslRepository postsQuerydslRepository;

//    게시글 페이징 조회
    @GetMapping("/posts")
    public List<PostsDto> getPosts(@PageableDefault Pageable pageable) {
        return postsQuerydslRepository.findAll(pageable);
    }

//    게시글 보기
    @GetMapping("/post/{id}")
    public PostDto post(@PathVariable("id") Long id) {
        PostDto result = postsService.findPostById(id);
        return result;
    }

    @PostMapping("/post/write")
    public String writePost(@RequestBody CreatePostDto requestData) {
        return postsService.writePost(requestData);
    }

    @DeleteMapping("/post/delete")
    public String deletePost(@RequestBody DeleteDto deleteDto) {
        return postsService.deletePost(deleteDto);
    }

    @PutMapping("/post/modified")
    public String modified(@RequestBody ModifiedDto modifiedDto) {
        return postsService.modified(modifiedDto);
    }
}
