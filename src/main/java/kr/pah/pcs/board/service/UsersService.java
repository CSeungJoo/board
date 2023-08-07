package kr.pah.pcs.board.service;

import kr.pah.pcs.board.domain.Role;
import kr.pah.pcs.board.domain.Users;
import kr.pah.pcs.board.dto.UserDto;
import kr.pah.pcs.board.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class UsersService {

    private final UsersRepository usersRepository;

    public ResponseEntity sign(UserDto.SignUserDto signUserDto) {
        if(usersRepository.findByUsername(signUserDto.getUsername()) == null) {
            Users user = new Users(null, signUserDto.getUsername(), signUserDto.getPassword(), signUserDto.getEmail(), Role.USER);
            usersRepository.save(user);
            return new ResponseEntity("정상적으로 회원가입되었습니다." , HttpStatus.OK);
        }else
            return new ResponseEntity("이미 존재하는 회원입니다.", HttpStatus.OK);
    }

    public Users login(UserDto.LoginDto loginDto) {
        return usersRepository.findByUsernameAndPassword(loginDto.getUsername(), loginDto.getUsername());
    }
}
