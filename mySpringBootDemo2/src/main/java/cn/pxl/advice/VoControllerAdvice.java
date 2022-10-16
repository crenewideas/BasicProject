package cn.pxl.advice;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice(basePackages = "cn.pxl.controller.adviceTest.*",annotations = {Controller.class, RestController.class})
public class VoControllerAdvice {

    //在抛出异常后，返回格式化的Json数据。
    @ExceptionHandler(value = ClassNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String,Object> exceptionHander(HttpServletRequest request, ClassNotFoundException e){
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("code",HttpStatus.INTERNAL_SERVER_ERROR);
        stringObjectHashMap.put("msg",e.getMessage());
        return stringObjectHashMap;
    }

}
