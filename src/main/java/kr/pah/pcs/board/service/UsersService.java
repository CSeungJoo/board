package kr.pah.pcs.board.service;

import com.querydsl.core.Tuple;
import kr.pah.pcs.board.domain.Role;
import kr.pah.pcs.board.domain.Users;
import kr.pah.pcs.board.dto.SignUserDto;
import kr.pah.pcs.board.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UsersService {

    private final UsersRepository usersRepository;

    public ResponseEntity sign(SignUserDto signUserDto) {
        log.info(signUserDto.getUsername());
        Users user = new Users(null, signUserDto.getUsername(), signUserDto.getPassword(), signUserDto.getEmail(), Role.USER);
        usersRepository.save(user);
        return new ResponseEntity("ok" , HttpStatus.OK);
    }

    public List<Users> login(String username, String password) {
        return usersRepository.findByUsernameAndPassword(username, password);
    }
}
