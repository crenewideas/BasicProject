package cn.pxl.structurePattern.bridge;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public abstract class AbstractSale {
    private String price;
    private String type;
    public abstract String getSaleAndType();
}
