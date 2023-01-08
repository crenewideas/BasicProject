package cn.pxl.myspringbootdemo3.dao.mapper;

import cn.pxl.myspringbootdemo3.entity.common.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    public User getUserById(int id);

    @Insert("insert into user (`id`,`name`,`age`) values (#{id},#{name},#{age})")
    public int insertUser(User user);
}
