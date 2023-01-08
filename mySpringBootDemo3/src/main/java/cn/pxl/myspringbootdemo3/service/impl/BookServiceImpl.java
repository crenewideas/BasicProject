package cn.pxl.myspringbootdemo3.service.impl;

import cn.pxl.myspringbootdemo3.dao.mapper.BookMapper;
import cn.pxl.myspringbootdemo3.entity.common.Book;
import cn.pxl.myspringbootdemo3.service.intf.BookService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
    @Autowired
    private BookMapper bookMapper;

    @Override
    public Boolean save(Book book) {
        return bookMapper.insert(book) > 0;
    }

    @Override
    public Boolean update(Book book) {
        return bookMapper.updateById(book) > 0;
    }

    @Override
    public Boolean delete(Integer id) {
        return bookMapper.deleteById(id) > 0;
    }

    @Override
    public Book getById(Integer id) {
        return bookMapper.selectById(id);
    }

    @Override
    public List<Book> getAll() {
        log.debug("service debug 日志输出！");
        log.info("service info 日志输出！");
        log.error("service error 日志输出！");
        return bookMapper.selectList(null);
    }

    @Override
    public IPage<Book> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage,pageSize);
        bookMapper.selectPage(page,null);
        return page;
    }

    @Override
    public void updateById(Book book) {
        bookMapper.update(book,null);
    }

    @Override
    public void removeById(int id) {
        bookMapper.deleteById(id);
    }

    @Override
    public void page(IPage<Book> page) {
        bookMapper.selectPage(page,null);
    }
}
