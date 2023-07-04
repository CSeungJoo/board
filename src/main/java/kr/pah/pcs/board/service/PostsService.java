package kr.pah.pcs.board.service;

import jakarta.persistence.EntityManager;
import kr.pah.pcs.board.domain.Posts;
import kr.pah.pcs.board.dto.CreatePostDto;
import kr.pah.pcs.board.dto.PostDto;
import kr.pah.pcs.board.exception.CustomException;
import kr.pah.pcs.board.exception.ErrorCode;
import kr.pah.pcs.board.repository.PostsQuerydslRepository;
import kr.pah.pcs.board.repository.PostsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostsService {

    private final EntityManager em;

    private final PostsRepository postsRepository;
    private final PostsQuerydslRepository postsQuerydslRepository;

    public String writePost(CreatePostDto data) {
        if (data.getContent().isBlank() || data.getTitle().isBlank() || data.getUsers().getUsername().isBlank() || data.getUsers().getId() == null) throw new CustomException(ErrorCode.INVALID_POST_DATA);
        Posts post = new Posts(data.getTitle(), data.getContent(), data.getUsers());
        postsRepository.save(post);
        return "ok";
    }
}
