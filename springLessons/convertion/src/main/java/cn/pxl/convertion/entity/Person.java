package cn.pxl.convertion.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Properties;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Person {
    private Properties properties;
    private String propertiesAsString;
    private String personName;
    private String personAge;
}
