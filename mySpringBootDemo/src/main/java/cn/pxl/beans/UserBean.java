package cn.pxl.beans;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//@Component("userBean")
@ToString
@Data
public class UserBean {
    private AnimalBean animalBean;

    public UserBean(@Autowired @Qualifier("animalBean") AnimalBean animalBean) {
        System.out.println("当前animalBean将被注入！");
        this.animalBean = animalBean;
    }
}
