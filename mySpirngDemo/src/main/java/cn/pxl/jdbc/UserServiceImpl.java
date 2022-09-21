package cn.pxl.jdbc;

import cn.pxl.entity.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

public class UserServiceImpl implements UserService {

    private JdbcTemplate jdbcTemplate;

    public UserServiceImpl(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public void save(User user) {
        String sqlTemplate = "insert into User (id,userName,passWord,acctNbr,idNo,mobileNbr) values (?,?,?,?,?,?)";
        jdbcTemplate.update(sqlTemplate,
            user.getId(),
            user.getUserName(),
            user.getPassWord(),
            user.getAcctNbr(),
            user.getIdNo(),
            user.getMobileNbr());
        //测试事务是否生效
        throw new RuntimeException("aa");
    }

    @Override
    public List<User> selectAll() {
        String queryTemplate = "select * from User";
        return jdbcTemplate.query(queryTemplate,new UserRowMapper());
    }
}

