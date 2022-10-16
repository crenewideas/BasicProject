package cn.pxl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/international")
public class InternationController {
    @RequestMapping("/loadPage")
    public String loadPage(){
        return "i18n/internationalization";
    }

}
