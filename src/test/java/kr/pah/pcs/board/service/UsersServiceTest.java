package kr.pah.pcs.board.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import kr.pah.pcs.board.dto.SignUserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsersServiceTest {

    @PersistenceContext
    EntityManager em;

    @Autowired
    UsersService usersService;
    
    @Test
    public void sign() throws Exception {
        SignUserDto signUserDto = new SignUserDto("name", "password", "mail@mail");
        System.out.println(usersService.sign(signUserDto));
    }

}