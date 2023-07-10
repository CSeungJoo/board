package kr.pah.pcs.board.controller;

import kr.pah.pcs.board.dto.SignUserDto;
import kr.pah.pcs.board.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UsersService usersService;

    @PostMapping("/sign")
    public ResponseEntity sign(SignUserDto signUserDto) {
        return usersService.sign(signUserDto);
    }
}
