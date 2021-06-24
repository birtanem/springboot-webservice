package com.springboot.book.config.auth;

import com.springboot.book.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity //Spring Security설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .headers().frameOptions().disable()//h2-console 화면을 사용하기 위해 해당 옵션들 disable
                .and()
                    .authorizeRequests() //URL별 권한관리 설정하는 옵션의 시작점. 얘가 먼저 선언되야 아래의 antMatchers옵션 사용 가능.
                    .antMatchers("/","/css/**","/images/**","/js/**","/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                            //권환 관리 대상 지정 옵션. URL, HTTP메소드별로 관리 가능. "/"등 URL은 전체 열람권한(permitAll)
                            // /api/v1/**와 같은 주소의 API는 USER권한을 가진 사람만 사용.
                    .anyRequest().authenticated() //설정된 값 이외의 나머지 URL. authenticated이용해 나머지 url은 인증된 사용자만 이용 가능함.
                .and()
                    .logout()
                        .logoutSuccessUrl("/")  //logout설정의 진입점.
                .and()
                    .oauth2Login()//oauth2로그인 기능에 대한 설정의 진입점.
                        .userInfoEndpoint() //oauth2로그인 성공 후 사용자 정보를 가져올 떄의 설정 담당.
                            .userService(customOAuth2UserService);
                                //소셜 로그인 성공 시 진행할 userservice인터페이스의 구현체 등록.
                                // 리소드 서버(소설 서비스)에서 사용자 정보를 가져 온 상태에서 추가로 진행하고자 하는 기능 명시 가능.
    }
}
