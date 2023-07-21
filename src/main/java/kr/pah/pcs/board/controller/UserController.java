package kr.pah.pcs.board.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kr.pah.pcs.board.domain.Users;
import kr.pah.pcs.board.dto.LoginDto;
import kr.pah.pcs.board.dto.SignUserDto;
import kr.pah.pcs.board.exception.CustomException;
import kr.pah.pcs.board.exception.ErrorCode;
import kr.pah.pcs.board.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UsersService usersService;

    @PostMapping("/sign")
    public ResponseEntity sign(@RequestBody SignUserDto signUserDto) {
        return usersService.sign(signUserDto);
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginDto loginDto, HttpServletRequest request) {
        Users user = usersService.login(loginDto);
        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user.getId());
            return new ResponseEntity("ok", HttpStatus.OK);
        }else throw new CustomException(ErrorCode.INVALID_POST_DATA);
    }

    @GetMapping("logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) session.invalidate();
    }
}
