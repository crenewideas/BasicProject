package cn.pxl.json.common;

import java.io.Serializable;

public class Book implements Serializable {
    private String bookName;

    public String getBookName() {
        return bookName;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                '}';
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}
