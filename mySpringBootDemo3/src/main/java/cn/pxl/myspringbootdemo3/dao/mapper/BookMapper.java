package cn.pxl.myspringbootdemo3.dao.mapper;

import cn.pxl.myspringbootdemo3.entity.common.Book;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<Book> {
}