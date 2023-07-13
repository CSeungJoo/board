package kr.pah.pcs.board.service;

import jakarta.persistence.EntityManager;
import kr.pah.pcs.board.domain.Posts;
import kr.pah.pcs.board.dto.*;
import kr.pah.pcs.board.exception.CustomException;
import kr.pah.pcs.board.exception.ErrorCode;
import kr.pah.pcs.board.repository.PostsQuerydslRepository;
import kr.pah.pcs.board.repository.PostsRepository;
import kr.pah.pcs.board.repository.UsersRepository;
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
    private final UsersRepository usersRepository;

    public String writePost(CreatePostDto data) {
        if (data.getContent().isBlank() || data.getTitle().isBlank() || data.getUsers().getUsername().isBlank() || data.getUsers().getId() == null) throw new CustomException(ErrorCode.INVALID_POST_DATA);
        Posts post = new Posts(data.getTitle(), data.getContent(), data.getUsers());
        postsRepository.save(post);
        return "ok";
    }

    public PostDto findPostById(Long id) {
        PostDto result = postsQuerydslRepository.findPostById(id);
        if(result == null) throw new CustomException(ErrorCode.POST_NOT_FOUND);
        result.setView(result.getView() + 1);
        return result;
    }

    public String deletePost(DeleteDto deleteDto) {
        if (deleteDto.getUsers().getId().equals(usersRepository.findById(deleteDto.getUsers().getId()).get().getId()))
        postsRepository.deleteById(deleteDto.getId());
        else throw new CustomException(ErrorCode.INVALID_DELETE_REQUEST);
        return "ok";
    }
    public String modified(ModifiedDto modifiedDto) {
        if (modifiedDto.getUsers().getId().equals(usersRepository.findById(modifiedDto.getUsers().getId()).get().getId())) {
            Posts result = postsRepository.findById(modifiedDto.getId()).get();
            result.modified(modifiedDto.getTitle(), modifiedDto.getContent());
            postsRepository.save(result);
            System.out.println("result = " + result);
        }else {
            throw new CustomException(ErrorCode.INVALID_POST_DATA);
        }
        return "ok";
    }
}
