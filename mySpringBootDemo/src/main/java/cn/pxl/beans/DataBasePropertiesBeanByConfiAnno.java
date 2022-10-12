package cn.pxl.beans;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ToString
@Component
@ConfigurationProperties("database")
public class DataBasePropertiesBeanByConfiAnno {
    private String userName;
    private String passWord;
    private String url;
    private String driver;

    public void setUserName(String userName) {
        //System.out.println("userName"+userName);
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        //System.out.println("passWord"+passWord);
        this.passWord = passWord;
    }

    public void setUrl(String url) {
        //System.out.println("url"+url);
        this.url = url;
    }

    public void setDriver(String driver) {
        //System.out.println("driver"+driver);
        this.driver = driver;
    }
}
