package cn.pxl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import javax.sql.DataSource;


//* @deprecated Use a {@link org.springframework.security.web.SecurityFilterChain} Bean to
// * configure {@link HttpSecurity} or a {@link WebSecurityCustomizer} Bean to configure
// * {@link WebSecurity}

//@Component
public class MyWebSecurityConfigByMemory extends WebSecurityConfigurerAdapter {

    //用于Filter链
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
    //http安全请求
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
    }

    //用于构建用户具体权限控制。可以自定义用户服务信息。
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //1.内存用户签名服务，将用户信息存放到内存中，适用于测试环境快速搭建用户信息。
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        auth.inMemoryAuthentication().passwordEncoder(bCryptPasswordEncoder)
            .withUser("admin")
            .password("$2a$10$ESKP5bJJ.oddbiL99sMcZuyFeXdgI06jhQ86Wv13fglT4pik/iGRS")
            .roles("USER","ADMIN")
        .and()
            .withUser("pxl2")
            .password("$2a$10$MnwjBrvKdHz7sj0bgl8l3.cGizNJkesxxhTKBzwvQSmju6cRMA2/6")
            .roles("USER");

    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("abc"));
        System.out.println(bCryptPasswordEncoder.encode("pxl2"));
    }
}
