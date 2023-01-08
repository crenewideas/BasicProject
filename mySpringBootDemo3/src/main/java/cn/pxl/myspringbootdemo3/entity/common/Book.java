package cn.pxl.myspringbootdemo3.entity.common;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Book {
    private Integer id;
    private String type;
    private String name;
    private String description;
}