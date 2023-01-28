package cn.pxl.feign.fallback;

import cn.pxl.feign.clients.UserClient;
import cn.pxl.feign.pojo.user.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;

@Slf4j
public class UserClientFallbackFactory  implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable cause) {
        return new UserClient() {
            @Override
            public User findById(Long id) {
                log.error("查询用户异常", cause);
                return new User();
            }
        };
    }
}
