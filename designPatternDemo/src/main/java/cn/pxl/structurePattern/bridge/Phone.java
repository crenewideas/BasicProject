package cn.pxl.structurePattern.bridge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data
public abstract class Phone {
    //桥接的类型
    private AbstractSale abstractSale;
    public abstract String getPhone();
}
