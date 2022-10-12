package cn.pxl.beans;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component
public class DataBasePropertiesBean {
    @Value("${database.username}")
    private String userName;
    @Value("${database.password}")
    private String passWord;
    private String url;
    private String driver;
    @Value("${database.url}")
    public void setUrl(String url) {
        //System.out.println("setUrl :" + url);
        this.url = url;
    }
    @Value("${database.driver}")
    public void setDriver(String driver) {
        //System.out.println("setDriver :" + driver);
        this.driver = driver;
    }
}
