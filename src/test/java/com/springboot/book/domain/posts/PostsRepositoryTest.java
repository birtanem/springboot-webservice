package com.springboot.book.domain.posts;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest //H2데이터베이스를 자동으로 실행해 준다.
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    //Junit에서 단위 테스트가 끝날 때 마다 수행되는 메소드를 지정 함.
    //보통은 배포 전 전체 테스트 수행 시 테스트간 데이터 침범을 막기 위해 사용한다.
    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder()
                .title(title)
                .content(content)
                .author("testing@gmail.com")
                .build());
        //.save : 테이블posts에 insert/update쿼리를 실행.
        //id가 존재하면 update, 없으면 insert가 실행된다.

        //when
        List<Posts> postsList = postsRepository.findAll();
        //.findALl() : 테이블에 있는 모든 데이터를 조회함.

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);

    }
}
