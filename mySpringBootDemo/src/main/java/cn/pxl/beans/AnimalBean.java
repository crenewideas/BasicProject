package cn.pxl.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

//@Component("animalBean")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public class AnimalBean {
    @Value("animalName")
    private String animalName;
}
