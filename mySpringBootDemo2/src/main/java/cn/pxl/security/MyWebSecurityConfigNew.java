//package cn.pxl.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
//import org.springframework.security.web.SecurityFilterChain;
//
//import javax.servlet.Filter;
//import javax.servlet.http.HttpServletRequest;
//import java.util.List;
//
////* @deprecated Use a {@link org.springframework.security.web.SecurityFilterChain} Bean to
//// * configure {@link HttpSecurity} or a {@link WebSecurityCustomizer} Bean to configure
//// * {@link WebSecurity}
//
//@Configuration
//public class MyWebSecurityConfig {
//    //什么请求放行，什么请求需要验证。
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
//        httpSecurity.authorizeRequests()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .disable()
//                .csrf()
//                .disable();
//        return httpSecurity.build();
//    }
//
//    @Bean
//    public WebSecurityCustomizer wbSecurityCustomizer(){
//        return web -> web.ignoring().antMatchers("/login");
//    }
//}
