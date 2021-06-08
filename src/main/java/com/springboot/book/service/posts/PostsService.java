
package com.springboot.book.service.posts;

import com.springboot.book.domain.posts.Posts;
import com.springboot.book.domain.posts.PostsRepository;
import com.springboot.book.web.dto.PostsResponseDto;
import com.springboot.book.web.dto.PostsSaveRequestDto;
import com.springboot.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값을 하는 생성자를 생성해준다.
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        return new PostsResponseDto(entity);
    }
    //update기능에서 db에 쿼리를 날리는 부분이 없는 이유: JPA의 영속성 컨텍스트(엔티티를 영구 저장하는 환경)
}
