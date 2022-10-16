package cn.pxl.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OneController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable("id") int id){
        ServiceInstance serviceInstance = discoveryClient.getInstances("User").get(0);
        String serviceId = serviceInstance.getServiceId();
        int port = serviceInstance.getPort();
        System.out.println("user/" + id + ":" + serviceId +":"+port);
        return "success";
    }

}
