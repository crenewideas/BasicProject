package cn.pxl.myspringbootdemo3.controller;

import cn.pxl.myspringbootdemo3.entity.common.Book;
import cn.pxl.myspringbootdemo3.entity.result.ResultEntity;
import cn.pxl.myspringbootdemo3.service.intf.BookService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/getAll")
    public ResultEntity<List<Book>> getAll(){
        List<Book> allBooks = bookService.getAll();
        log.debug("查询所有Book列表返回结果：[{}]",allBooks);
        log.info("查询所有Book列表返回结果：[{}]",allBooks);
        log.error("查询所有Book列表返回结果：[{}]",allBooks);
        return ResultEntity.success(allBooks);
    }

    @PostMapping
    public Boolean save(@RequestBody Book book){
        return bookService.save(book);
    }

    @PutMapping
    public Boolean update(@RequestBody Book book){
        return bookService.update(book);
    }

    @DeleteMapping("{id}")
    public Boolean delete(@PathVariable Integer id){
        return bookService.delete(id);
    }

    @GetMapping("{id}")
    public Book getById(@PathVariable Integer id){
        return bookService.getById(id);
    }

    @GetMapping("{currentPage}/{pageSize}")
    public IPage<Book> getPage(@PathVariable int currentPage, @PathVariable int pageSize){
        return bookService.getPage(currentPage,pageSize);
    }
}