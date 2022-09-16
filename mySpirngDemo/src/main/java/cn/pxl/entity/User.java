package cn.pxl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User {
    private String id;
    private String userName;
    private String passWord;
    private String acctNbr;
    private String idNo;
    private String mobileNbr;
}
