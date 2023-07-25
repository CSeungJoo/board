package kr.pah.pcs.board.repository;

import kr.pah.pcs.board.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Users findByUsernameAndPassword(String username, String Password);

    Users findByUsername(String username);

    Users findUsersById(Long user);
}
