package kr.pah.pcs.board.service;

import kr.pah.pcs.board.domain.Role;
import kr.pah.pcs.board.domain.Users;
import kr.pah.pcs.board.dto.SignUserDto;
import kr.pah.pcs.board.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UsersService {

    private final UsersRepository usersRepository;

    public ResponseEntity sign(SignUserDto signUserDto) {
        Users user = new Users(null, signUserDto.getUsername(), signUserDto.getPassword(), signUserDto.getEmail(), Role.USER);
        usersRepository.save(user);
        return new ResponseEntity("ok" , HttpStatus.OK);
    }
}
