package kr.pah.pcs.board.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.pah.pcs.board.dto.CommentDto;
import kr.pah.pcs.board.exception.CustomException;
import kr.pah.pcs.board.exception.ErrorCode;
import kr.pah.pcs.board.repository.CommentQuerydslRepository;
import kr.pah.pcs.board.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CommentController {

    private static CommentService commentService;
    private static CommentQuerydslRepository commentQuerydslRepository;

    @PostMapping("/comment/write")
    public ResponseEntity writeComment(@RequestBody CommentDto.WriteCommentDto writeCommentDto, HttpServletRequest request) {
        HttpSession session = request.getSession(true);
        if(session.getAttribute("user") != null)
            return commentService.writeComment(writeCommentDto, request);
        else
            throw new CustomException(ErrorCode.INVALID_SESSION_DATA);
    }

    @GetMapping("/comment/{id}")
    public List<CommentDto.GetCommentDto> getComment(@PathVariable("id") Long id , @PageableDefault Pageable pageable) {
        return commentQuerydslRepository.findAllByPost(id, pageable);
    }

    @PutMapping("/comment/modified")
    public ResponseEntity modifiedComment(@RequestBody CommentDto.ModifiedCommentDto modifiedCommentDto, HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("user") != null)
            return commentService.modifiedComment(modifiedCommentDto, request);
        else
            throw new CustomException(ErrorCode.INVALID_SESSION_DATA);
    }

    @DeleteMapping("comment/delete")
    public ResponseEntity deleteComment(@RequestBody Long id ,HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null)
            return commentService.deleteComment(id, request);
        else
            throw new CustomException(ErrorCode.INVALID_SESSION_DATA);
    }
}
