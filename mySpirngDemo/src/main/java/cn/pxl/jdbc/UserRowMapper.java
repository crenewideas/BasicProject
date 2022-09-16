package cn.pxl.jdbc;

import cn.pxl.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User(
            rs.getString("id"),
            rs.getString("userName"),
            rs.getString("passWord"),
            rs.getString("acctNbr"),
            rs.getString("idNo"),
            rs.getString("mobileNbr")
        );
    }
}
