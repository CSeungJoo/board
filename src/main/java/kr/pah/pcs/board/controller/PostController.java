package kr.pah.pcs.board.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.pah.pcs.board.dto.CreatePostDto;
import kr.pah.pcs.board.dto.PostDto;
import kr.pah.pcs.board.dto.PostsDto;
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

    @Autowired
    PostsQuerydslRepository postsQuerydslRepository;

//    게시글 페이징 조회
    @GetMapping("/posts")
    public List<PostsDto> getPosts(@PageableDefault Pageable pageable) {
        return postsQuerydslRepository.findAll(pageable);
    }

//    게시글 보기
    @GetMapping("/post/{id}")
    public PostDto post(@PathVariable("id") Long id) {
        PostDto result = postsQuerydslRepository.findPostById(id);
        if(result == null) throw new CustomException(ErrorCode.POST_NOT_FOUND);
        result.setView(result.getView() + 1);
        return result;
    }

    @PostMapping("/post/write")
    public String write(@RequestBody CreatePostDto requestData) {
        return postsService.writePost(requestData);
    }
}
