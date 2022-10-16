package cn.pxl.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;

//@Component
public class MyWebSecurityConfigByUserDetail extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource = null;

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
