package com.healing.healingdog.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public void configure(WebSecurity web) throws Exception {
//        web
//                // 외부에서 이미지 파일에 접근 가능 하도록 설정
//                .ignoring()
//                .antMatchers("/productimgs/**");
//
//    }
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .antMatchers("/static/itemsImg/**");
    }

    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
    //@Override
   // protected void configure(HttpSecurity http) throws Exception {


         // CSRF 설정 Disable
        http.csrf().disable()
                .formLogin().disable()//Spring Security가 기본적으로 제공하는 formLogin 기능을 사용하지 않겠다는것
                .httpBasic().disable()// 매 요청마다 id, pwd를 보내는 방식으로 인증하는 httpBasic를 사용하지 않겠다는것

                // 시큐리티는 기본적으로 세션을 사용하지만 API 서버에선 세션을 사용하지 않기 때문에 세션 설정을 Stateless 로 설정
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()// http servletRequest 를 사용하는 요청들에 대한 접근제한을 설정
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/**").permitAll()// 로그인, 회원가입 API 는 토큰이 없는 상태에서 요청이 들어오기 때문에 permitAll 설정

//                .antMatchers("/api/**").hasAnyRole("USER", "ADMIN") // 나머지 API 는 전부 인증 필요
                .and()
                .cors();

        return http.build();
    }

}