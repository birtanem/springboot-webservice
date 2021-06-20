package com.springboot.book.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    GUSET("ROLE_GUEST","손님"),
    USER("ROLE_USER","일반 사용자");
    //스프링 시큐리티에서 권한 코드는 항상 ROLE_ 이 앞에 붙는다.
    private final String key;
    private final String title;

}
