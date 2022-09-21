package cn.pxl.mvc;

import cn.pxl.bean.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UserController extends AbstractController{
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = new User();
        user.setUserName("pxl");
        user.setPassWord("aaa");
        return new ModelAndView("userList","users",user);
    }
}
