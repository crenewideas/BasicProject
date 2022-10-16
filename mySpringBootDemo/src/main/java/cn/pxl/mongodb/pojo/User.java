//package cn.pxl.mongodb.pojo;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//import org.springframework.data.mongodb.core.mapping.Field;
//
//import java.io.Serializable;
//import java.util.List;
//
////表示为Mongodb文档
//@Document
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
//public class User implements Serializable {
//    //Mongodb文档编号，主键
//    @Id
//    private long id;
//
//    //user_name保存属性
//    @Field("user_name")
//    private String userName = null;
//
//    private String note = null;
//
//    private List<Role> roleList = null;
//
//}
