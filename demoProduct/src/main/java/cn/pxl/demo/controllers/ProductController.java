package cn.pxl.demo.controllers;

import cn.pxl.demo.intf.FeignInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private FeignInterface feignInterface;

    @GetMapping("/ribbon")
    public void testRibbon(){
        for (int i = 0; i < 10; i++) {
            restTemplate.getForObject("http://USER/user/" + (i+1),String.class);
        }
    }

    @GetMapping("/feign")
    public void testFeign(){
        for (int i = 0; i < 10; i++) {
            feignInterface.getUser(i);
        }
    }

}
