package cn.pxl.myspringbootdemo3.dao.mapper;

import cn.pxl.myspringbootdemo3.entity.common.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PlusUserMapper extends BaseMapper<User> {

}
