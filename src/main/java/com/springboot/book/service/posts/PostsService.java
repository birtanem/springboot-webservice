
package com.springboot.book.service.posts;

import com.springboot.book.domain.posts.Posts;
import com.springboot.book.domain.posts.PostsRepository;
import com.springboot.book.web.dto.PostsListResponseDto;
import com.springboot.book.web.dto.PostsResponseDto;
import com.springboot.book.web.dto.PostsSaveRequestDto;
import com.springboot.book.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+id));

        postsRepository.delete(posts); //jpa메소드.
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        return new PostsResponseDto(entity);
    }

    //readOnly 옵션추가. 트랜잭션 범위는 유지하고 조회기능만 남겨두어 조회속도가 개선됨. 등록,수정,삭제 기능이 없는 서비스메서드에서 사용할 것.
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new) //map(posts -> new PostsListResponseDto(posts))
                .collect(Collectors.toList());
        //postsRepository의 결과로 넘어온 Posts의 Stream을 map을 통해 PostsListResponseDto변환 -> List반환
    }
    //update기능에서 db에 쿼리를 날리는 부분이 없는 이유: JPA의 영속성 컨텍스트(엔티티를 영구 저장하는 환경)
}
