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
    public List<PostsDto> getPosts(@RequestParam(required = false) String title,@PageableDefault Pageable pageable) {
        if (title != null) {
            return postsQuerydslRepository.findAll(pageable);
        }else {
            return postsQuerydslRepository.findAllByTitle(title, pageable);
        }
    }

//    게시글 보기
    @GetMapping("/post/{id}")
    public PostDto post(@PathVariable("id") Long id) {
        return postsService.findPostById(id);
    }

    @PostMapping("/post/write")
    public String writePost(@RequestBody CreatePostDto createPostDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session.getAttribute("user") != null)
            return postsService.writePost(createPostDto);
        else
            throw new CustomException(ErrorCode.INVALID_SESSION_DATA);
    }

    @DeleteMapping("/post/delete")
    public String deletePost(@RequestBody DeleteDto deleteDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session.getAttribute("user") != null)
            return postsService.deletePost(deleteDto, request);
        else
            throw new CustomException(ErrorCode.INVALID_SESSION_DATA);
    }

    @PutMapping("/post/modified")
    public String modified(@RequestBody ModifiedDto modifiedDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session.getAttribute("user") != null)
        return postsService.modified(modifiedDto, request);
        else
            throw new CustomException(ErrorCode.INVALID_SESSION_DATA);
    }

    @PostMapping("/comment/write")
    public ResponseEntity writeComment(@RequestBody WriteCommentDto writeCommentDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session.getAttribute("user") != null)
            return postsService.writeComment(writeCommentDto, request);
        else
            throw new CustomException(ErrorCode.INVALID_SESSION_DATA);
    }

    @GetMapping("/comment/{id}")
    public List<GetCommentDto> getComment(@PathVariable("id") Long id ,@PageableDefault Pageable pageable) {
        return commentQuerydslRepository.findAllByPost(id, pageable);
    }

    @PutMapping("/comment/modified")
    public ResponseEntity modifiedComment(@RequestBody ModifiedCommentDto modifiedCommentDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null)
            return postsService.modifiedComment(modifiedCommentDto, request);
        else
            throw new CustomException(ErrorCode.INVALID_SESSION_DATA);
    }

    @DeleteMapping("comment/delete")
    public ResponseEntity deleteComment(@RequestBody Long id ,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null)
            return postsService.deleteComment(id, request);
        else
            throw new CustomException(ErrorCode.INVALID_SESSION_DATA);
    }
}
