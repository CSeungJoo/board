package kr.pah.pcs.board.repository;

import kr.pah.pcs.board.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
