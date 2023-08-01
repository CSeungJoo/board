package kr.pah.pcs.board.controller;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.pah.pcs.board.domain.Comment;
import kr.pah.pcs.board.domain.Posts;
import kr.pah.pcs.board.domain.Users;
import kr.pah.pcs.board.dto.*;
import kr.pah.pcs.board.exception.CustomException;
import kr.pah.pcs.board.exception.ErrorCode;
import kr.pah.pcs.board.repository.CommentQuerydslRepository;
import kr.pah.pcs.board.repository.PostsQuerydslRepository;
import kr.pah.pcs.board.repository.PostsRepository;
import kr.pah.pcs.board.service.PostsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:80")
public class PostController {

    private final EntityManager em;

    private final PostsRepository postsRepository;
    private final PostsService postsService;
    private final PostsQuerydslRepository postsQuerydslRepository;
    private final CommentQuerydslRepository commentQuerydslRepository;

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

    @PostMapping("/comment")
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
