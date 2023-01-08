package cn.pxl.demo.intf;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user")
public interface FeignInterface {
    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") int id);
}
