package cn.pxl.feign.clients;


import cn.pxl.feign.fallback.UserClientFallbackFactory;
import cn.pxl.feign.pojo.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//TODO UserClientFallbackFactory 注册有问题！
//@FeignClient(value = "userservice",fallbackFactory = UserClientFallbackFactory.class)
@FeignClient(value = "userservice")
public interface UserClient {
    @GetMapping("/user/common/{id}")
    User findById(@PathVariable("id") Long id);
}
