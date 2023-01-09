package cn.pxl.feign.clients;


import cn.pxl.feign.pojo.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "userservice")
public interface UserClient {

    @GetMapping("/user/common/{id}")
    User findById(@PathVariable("id") Long id);
}
