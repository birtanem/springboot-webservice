package com.springboot.book.config.auth.dto;

import com.springboot.book.domain.user.User;
import lombok.Getter;

// 인증된 사용자 정보를 담는 필드.

@Getter
public class SessionUser {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
