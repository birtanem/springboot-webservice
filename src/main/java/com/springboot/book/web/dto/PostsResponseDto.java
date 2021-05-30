package com.springboot.book.web.dto;

import com.springboot.book.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {
    //Entity의 필드 중 일부만 사용하는 클래스. 생성자로 Entity를 받아 필드에 값을 넣는다.

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
