package cn.pxl.myspringbootdemo3;

import cn.pxl.myspringbootdemo3.dao.mapper.BookMapper;
import cn.pxl.myspringbootdemo3.entity.common.Book;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyBookTests {

    @Autowired
    private BookMapper bookMapper;

    @Test
    void testGetById(){
        System.out.println(bookMapper.selectById(1));
    }

    @Test
    void testSave(){
        Book book = new Book();
        book.setType("测试数据123");
        book.setName("测试数据123");
        book.setDescription("测试数据123");
        bookMapper.insert(book);
    }

    @Test
    void testUpdate(){
        Book book = new Book();
        book.setId(17);
        book.setType("测试数据abcdefg");
        book.setName("测试数据123");
        book.setDescription("测试数据123");
        bookMapper.updateById(book);
    }

    @Test
    void testDelete(){
        bookMapper.deleteById(16);
    }

    @Test
    void testGetAll(){
        bookMapper.selectList(null);
    }

    //分页查询
    @Test
    void testGetPage(){
        IPage page = new Page(2,5);
        bookMapper.selectPage(page, null);
        System.out.println(page.getCurrent());
        System.out.println(page.getSize());
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
        System.out.println(page.getRecords());
    }

    //模糊查询
    @Test
    void testGetBy(){
        QueryWrapper<Book> qw = new QueryWrapper<>();
        qw.like("name","Spring");
        bookMapper.selectList(qw);
    }

    //为了便于开发者动态拼写SQL，防止将null数据作为条件使用，MyBatisPlus还提供了动态拼装SQL的快捷书写方式。
    @Test
    void testGetBy2(){
        String name = "1";
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper();
        //if(name != null) lqw.like(Book::getName,name);		//方式一：JAVA代码控制
        lqw.like(name != null,Book::getName,name);		//方式二：API接口提供控制开关
        bookMapper.selectList(lqw);
    }

}
