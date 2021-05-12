package com.springboot.book;

import com.springboot.book.web.dto.HelloResponseDto;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Test;


public class HelloResponseDtoTest {

    @Test
    public void 롬복_기능_테스트(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
        //assertj라는 테스트 검증 라이브러리의 검증 메소드.
        //검증하고 싶은 대상을 메소드 인자로 받는다.
        //Junit의 assertThat이 아닌 assertj의 assertThat을 사용함. 그이유는?
        //http://bit.ly/30vm9Lg

    }
}
