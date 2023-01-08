package cn.pxl.myspringbootdemo3.service.intf;

import cn.pxl.myspringbootdemo3.entity.common.Book;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

public interface BookService{
    Boolean save(Book book);
    Boolean update(Book book);
    Boolean delete(Integer id);
    Book getById(Integer id);
    List<Book> getAll();
    IPage<Book> getPage(int currentPage, int pageSize);
    void updateById(Book book);
    void removeById(int id);
    void page(IPage<Book> page);
}