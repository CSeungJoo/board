package kr.pah.pcs.board.service;

import jakarta.persistence.EntityManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.pah.pcs.board.domain.Comment;
import kr.pah.pcs.board.domain.Posts;
import kr.pah.pcs.board.domain.Users;
import kr.pah.pcs.board.dto.*;
import kr.pah.pcs.board.exception.CustomException;
import kr.pah.pcs.board.exception.ErrorCode;
import kr.pah.pcs.board.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PostsService {

    private final EntityManager em;

    private final PostsRepository postsRepository;
    private final PostsQuerydslRepository postsQuerydslRepository;
    private final CommentRepository commentRepository;
    private final UsersRepository usersRepository;
    private final CommentQuerydslRepository commentQuerydslRepository;

    public String writePost(CreatePostDto data) {
        if (data.getContent().isBlank() || data.getTitle().isBlank() || data.getUsers() == null || data.getUsers().getId() == null) throw new CustomException(ErrorCode.INVALID_POST_DATA);
        Posts post = new Posts(data.getTitle(), data.getContent(), data.getUsers());
        postsRepository.save(post);
        return "ok";
    }

    public PostDto findPostById(Long id) {
        PostDto result = postsQuerydslRepository.findPostById(id);
        if(result == null) throw new CustomException(ErrorCode.POST_NOT_FOUND);
        result.setView(result.getView() + 1);
        return result;
    }

    public String deletePost(DeleteDto deleteDto, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Long users = deleteDto.getUsers().getId();
        if (users.equals(session.getAttribute("user")))
        postsRepository.deleteById(deleteDto.getId());
        else throw new CustomException(ErrorCode.INVALID_DELETE_REQUEST);
        return "ok";
    }
    public String modified(ModifiedDto modifiedDto, HttpServletRequest request) {
        Long posts = modifiedDto.getUsers().getId();
        HttpSession session = request.getSession(false);
        if (posts.equals(session.getAttribute("user"))) {
            Posts result = postsRepository.findById(modifiedDto.getId()).get();
            result.modified(modifiedDto.getTitle(), modifiedDto.getContent());
        }
        else
            throw new CustomException(ErrorCode.INVALID_POST_DATA);
        return "ok";
    }

    public ResponseEntity writeComment(WriteCommentDto writeCommentDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Posts post = postsRepository.findPostsById(writeCommentDto.getPost_id());
        Users user = usersRepository.findUsersById((Long) session.getAttribute("user"));
        commentRepository.save(new Comment(null, writeCommentDto.getComment(), post, user));
        return new ResponseEntity("정상적으로 댓글이 작성되었습니다.", HttpStatus.OK);
    }

    public ResponseEntity modifiedComment(ModifiedCommentDto modifiedCommentDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (modifiedCommentDto.getUsers().getId().equals(session.getAttribute("user"))) {
            Comment comment = commentQuerydslRepository.findCommentById(modifiedCommentDto.getId());
            comment.changeComment(modifiedCommentDto.getComment());
            return new ResponseEntity("정상적으로 수정되었습니다.", HttpStatus.OK);
        }else {
            throw new CustomException(ErrorCode.INVALID_DELETE_REQUEST);
        }
    }

    public ResponseEntity deleteComment(Long id, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        Comment comment = commentQuerydslRepository.findCommentById(id);
        if (comment.getUsers().getId().equals((Long)session.getAttribute("user"))) {
            commentRepository.deleteById(id);
            return new ResponseEntity("정상적으로 삭제되었습니다.", HttpStatus.OK);
        }else
            throw new CustomException(ErrorCode.INVALID_SESSION_DATA);
    }
}
