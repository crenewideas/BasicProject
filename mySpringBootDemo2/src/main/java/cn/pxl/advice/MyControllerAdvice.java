package cn.pxl.advice;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice(basePackages = "cn.pxl.controller.adviceTest.*")
public class MyControllerAdvice {

    //绑定格式化、参数转换规则和增加验证器等。会在参数转化之前执行。
    @InitBinder
    public void initDataBinder(WebDataBinder binder){
        //自定义日期编辑器，限定格式，且参数不允许为空
        CustomDateEditor customDateEditor = new CustomDateEditor(new SimpleDateFormat("yyyy-mm-dd"), false);
        binder.registerCustomEditor(Date.class,customDateEditor);
    }


    //在执行控制器之前，对数据模型初始化或操作。
    @ModelAttribute()
    public void initModule(Model model){
        //初始化模型数据。
        model.addAttribute("initOne","initOneValue");
    }

    //在抛出异常后，拦截异常并处理返回视图。
    @ExceptionHandler(value = Exception.class)
    public String exceptionHander(Model model,Exception e){
        //返回模型数据 错误信息。
        model.addAttribute("exceptionMsg",e.getMessage());
        //返回视图名称为 exception.jsp
        return "exception";
    }

}
