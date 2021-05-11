package com.springboot.book;

import com.springboot.book.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)    //스프링부트테스트와 JUnit사이의 연결자 역할.
@WebMvcTest(controllers = HelloController.class)
    //Web(Spring MVC)에 집중할 수 있는 어노테이션.
    //선언할 경우 @Controller, @ControllerAdvice등을 사용할 수 있으나
    //@Service, @Component, @Repository등은 사용할 수 없다.
    //테스트코드에서는 controller만 사용하기 때문에 선언함.
public class HelloControllerTest {

    @Autowired  //Bean 주입받기.
    private MockMvc mvc;
        //웹API를 테스트 할 때 사용, 스프링MVC 테스트의 시작점, 이 클래스를 통해 http get, post등에 대한 api 테스트 가능.

    @Test
    public void hello_가리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello"))  //MockMvc통해 /hello주소로 http get요청. 체이닝지원
                .andExpect(status().isOk()) //mvc.perform에 대한 결과 검증. HTTP Header의 Status검증. isOk: 200인지 아닌지 검증.
                .andExpect(content().string(hello));    //응답 본문의 내용을 검증한다. Controller에서 리턴된 "hello"가 맞는지 검증.
    }
}
