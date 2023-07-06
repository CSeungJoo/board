package kr.pah.pcs.board.repository;

import kr.pah.pcs.board.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
