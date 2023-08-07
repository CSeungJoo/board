package kr.pah.pcs.board.service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.pah.pcs.board.domain.Comment;
import kr.pah.pcs.board.domain.Posts;
import kr.pah.pcs.board.domain.Users;
import kr.pah.pcs.board.dto.CommentDto;
import kr.pah.pcs.board.exception.CustomException;
import kr.pah.pcs.board.exception.ErrorCode;
import kr.pah.pcs.board.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final PostsRepository postsRepository;
    private final PostsQuerydslRepository postsQuerydslRepository;
    private final CommentRepository commentRepository;
    private final UsersRepository usersRepository;
    private final CommentQuerydslRepository commentQuerydslRepository;


    public ResponseEntity writeComment(CommentDto.WriteCommentDto writeCommentDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        Posts post = postsRepository.findPostsById(writeCommentDto.getPost_id());
        Users user = usersRepository.findUsersById((Long) session.getAttribute("user"));
        commentRepository.save(new Comment(null, writeCommentDto.getComment(), post, user));
        return new ResponseEntity("정상적으로 댓글이 작성되었습니다.", HttpStatus.OK);
    }

    public ResponseEntity modifiedComment(CommentDto.ModifiedCommentDto modifiedCommentDto, HttpServletRequest request) {
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
