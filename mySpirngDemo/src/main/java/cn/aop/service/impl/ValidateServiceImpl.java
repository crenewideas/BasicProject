package cn.aop.service.impl;

import cn.aop.service.ValidateService;
import org.springframework.stereotype.Component;

@Component
public class ValidateServiceImpl implements ValidateService {
    @Override
    public boolean checkUser() {
        System.out.println("@DeclareParents");
        return false;
    }
}
