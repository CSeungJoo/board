package kr.pah.pcs.board.controller;

import jakarta.servlet.http.*;
import kr.pah.pcs.board.dto.*;
import kr.pah.pcs.board.exception.*;
import kr.pah.pcs.board.repository.CommentQuerydslRepository;
import kr.pah.pcs.board.repository.PostsQuerydslRepository;
import kr.pah.pcs.board.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:80")
public class PostController {

    private final PostsService postsService;
    private final PostsQuerydslRepository postsQuerydslRepository;
    private final CommentQuerydslRepository commentQuerydslRepository;

//    게시글 페이징 조회
    @GetMapping("/posts")
    public List<PostDto.GetPostsDto> getPosts(@RequestParam(required = false) String title,@PageableDefault Pageable pageable) {
        if (title == null) {
            return postsQuerydslRepository.findAll(pageable);
        }else {
            return postsQuerydslRepository.findAllByTitle(title, pageable);
        }
    }

//    게시글 보기
    @GetMapping("/post/{id}")
    public PostDto.GetPostDto post(@PathVariable("id") Long id) {
        return postsService.findPostById(id);
    }

    @PostMapping("/post/write")
    public ResponseEntity writePost(@RequestBody PostDto.CreatePostDto createPostDto, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if(session.getAttribute("user") != null)
            return postsService.writePost(createPostDto);
        else
            throw new CustomException(ErrorCode.INVALID_SESSION_DATA);
    }

    @DeleteMapping("/post/delete")
    public ResponseEntity deletePost(@RequestBody PostDto.DeleteDto deleteDto, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if(session.getAttribute("user") != null)
            return postsService.deletePost(deleteDto, request);
        else
            throw new CustomException(ErrorCode.INVALID_SESSION_DATA);
    }

    @PutMapping("/post/modified")
    public ResponseEntity modified(@RequestBody PostDto.ModifiedPostDto modifiedDto, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if(session.getAttribute("user") != null)
        return postsService.modified(modifiedDto, request);
        else
            throw new CustomException(ErrorCode.INVALID_SESSION_DATA);
    }

}
