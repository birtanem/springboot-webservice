package com.springboot.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//--------------
//메인클래스.
//@SpringBootApplication으로 자동설정, bean읽기와 생성을 자동으로 함.
// 어노테이션이 있는 위치부터 설정을 읽으므로 프로젝트 최상단에 위치해야 함.

@SpringBootApplication
public class Application {
    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

}
