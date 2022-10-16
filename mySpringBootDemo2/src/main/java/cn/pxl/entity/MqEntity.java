package cn.pxl.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MqEntity implements Serializable {

    private static final long serialVersionUID = -6548468428606488817L;

    private Long id;
    private String userName;
    private String note;

}
