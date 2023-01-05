package cn.pxl.data.binding;

import cn.pxl.entity.User;
import cn.pxl.entity.UserHolder;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.DataBinder;
import java.util.HashMap;

public class Demo01DataBind {
    public static void main(String[] args) {
//        bindingDemo();
//        bindingDemo2();
        bindingDemo3();
    }

    private static void bindingDemo(){
        User user = new User();
        //创建 DataBinder
        DataBinder dataBinder = new DataBinder(user, "user");
        //创建
        HashMap<String, Object> source = new HashMap<>();
        source.put("userName","pxl666");
        source.put("passWord","passWord666");
        MutablePropertyValues propertyValues = new MutablePropertyValues(source);
        //绑定 MutablePropertyValues 值到 DataBinder 中的 user 上。
        dataBinder.bind(propertyValues);
        System.out.println(user);
    }

    // MutablePropertyValues 中存在 user 中不存在的属性
    private static void bindingDemo2(){
        User user = new User();
        //创建 DataBinder
        DataBinder dataBinder = new DataBinder(user, "user");
        //创建
        HashMap<String, Object> source = new HashMap<>();
        source.put("userName","pxl666");
        source.put("passWord","passWord666");
        //passWord2在user中不存在，不会注入值。会忽略这个值。
        source.put("passWord2","passWord666");
        MutablePropertyValues propertyValues = new MutablePropertyValues(source);
        //绑定 MutablePropertyValues 值到 DataBinder 中的 user 上。
        dataBinder.bind(propertyValues);
        System.out.println(user);
    }

    //存在嵌套属性
    private static void bindingDemo3(){
        UserHolder userHolder = new UserHolder();
        //创建 DataBinder
        DataBinder dataBinder = new DataBinder(userHolder, "userHolder");
        //创建 复合对象 的数据源
        HashMap<String, Object> source = new HashMap<>();
        source.put("user.userName","pxl888");
        source.put("user.passWord","passWord888");
        MutablePropertyValues propertyValues = new MutablePropertyValues(source);
        //绑定 MutablePropertyValues 值到 DataBinder 中的 user 上。
        dataBinder.bind(propertyValues);
        System.out.println(userHolder);
    }

}
