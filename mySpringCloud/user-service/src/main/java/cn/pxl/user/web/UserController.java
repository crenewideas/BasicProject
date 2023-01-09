package cn.pxl.user.web;

import cn.pxl.commonutils.result.ResultEntity;
import cn.pxl.feign.pojo.user.User;
import cn.pxl.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
//这个注解，开启 nacos 热配置更新；如果不加，nacos远程配置更新后需要重启系统！
@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

//    @Value("${pattern.dateformat}")
//    private String pattern;
//
//    @Autowired
//    private Pattern pattern2;

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public ResultEntity<User> queryById(@PathVariable("id") Long id) {
        return ResultEntity.success(queryCommonById(id));
    }

    @GetMapping("/common/{id}")
    public User queryCommonById(@PathVariable("id") Long id) {
        queryProp();
        return userService.findById(id);
    }

    @GetMapping("/prop")
    public void queryProp() {
//        //读取配置文件方式一
//        System.out.println(pattern);
//        //读取配置文件方式二
//        System.out.println(pattern2.getDateFormat());
//        System.out.println(pattern2.getEnvSharedValue());
    }
}
