package cn.pxl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

//* @deprecated Use a {@link org.springframework.security.web.SecurityFilterChain} Bean to
// * configure {@link HttpSecurity} or a {@link WebSecurityCustomizer} Bean to configure
// * {@link WebSecurity}

@Component
public class MyWebSecurityConfigBySql extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource = null;

    //用于Filter链
    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
    }
    //http安全请求,对不同的角色赋予不同的权限。
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //配置方式1：登录认证成功时，可以访问所有的资源
//        http.authorizeRequests().anyRequest().authenticated()//所有的请求，签名成功的用户都进行授权访问。
//            .and().formLogin()//使用 security 的默认登录界面
//            .and().httpBasic()//启用 http 的基础验证。

        //配置方式2：根据用户角色赋予不同的权限。
        http.authorizeRequests()
            //对于 /entity/ 请求的访问，需要是 ADMIN 角色。
            .antMatchers("/entity/**").hasAnyRole("ADMIN")
            .antMatchers("/restTemplate/**").hasAnyAuthority("ROLE_USER")    //非ROLE_USER权限而请求时，前端会返回There was an unexpected error (type=Forbidden, status=403). Forbidden
            //其他路径，允许签名后访问。
            .anyRequest().permitAll()
            //没有配置权限的其他请求，允许匿名访问
            .and().anonymous()
            .and().formLogin()
            .and().httpBasic();
    }

    //用于构建用户具体权限控制。可以自定义用户服务信息。
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        //2.使用数据库定义用户认证服务。
        //返回三列，分别是用户名，密码和用户是否有效标识。
        String queryUserSql = "select user_name,pass_word,available from Auth_User where user_name = ?";
        //返回某个用户具有的角色信息。
        String queryRoleSql = "select au.user_name,ar.role_name from Auth_Role ar,Auth_User au,Auth_User_Role aur " +
                "where aur.user_id = au.id and aur.role_id = ar.id and user_name = ?";
        auth.jdbcAuthentication()
            .passwordEncoder(bCryptPasswordEncoder)
            .dataSource(dataSource)
            //查询用户，自动判断密码是否一致。
            .usersByUsernameQuery(queryUserSql)
            //赋予权限。如果 queryRoleSql 返回多个权限信息，那么该用户就会被赋予多个权限。
            .authoritiesByUsernameQuery(queryRoleSql);

    }

    public static void main(String[] args) {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        System.out.println(bCryptPasswordEncoder.encode("abc"));
        System.out.println(bCryptPasswordEncoder.encode("pxl2"));
    }
}
