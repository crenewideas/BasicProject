package cn.pxl.json;

import cn.pxl.json.common.Book;
import cn.pxl.json.common.School;
import cn.pxl.json.common.User;

import java.security.SecureClassLoader;
import java.util.ArrayList;

public class JsonTest {

    public static User objectToJson(){
        User user = new User();
        user.setAge(14);
        user.setName("彭笑良");
        School school = new School();
        school.setTeacherName("马老师");
        ArrayList<Book> books = new ArrayList<>();
        Book book = new Book();
        Book book2 = new Book();
        book.setBookName("书名1");
        book2.setBookName("书名2");
        books.add(book);
        books.add(book2);
        user.setSchool(school);
        user.setBooks(books);
        return user;
    }

}
