package cn.pxl.user.web;

import cn.pxl.commonutils.result.ResultEntity;
import cn.pxl.user.pojo.User;
import cn.pxl.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

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
        return userService.queryById(id);
    }
}
