package kr.pah.pcs.board.repository;

import kr.pah.pcs.board.domain.Posts;
import kr.pah.pcs.board.dto.PostsDto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
