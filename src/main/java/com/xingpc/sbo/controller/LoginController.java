package com.xingpc.sbo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Author: XingPc
 * @Description: ${description}
 * @Date: 2020/2/19 17:55
 * @Version: 1.0
 */
@Controller
@RequestMapping(value = "/user")
public class LoginController {

    private static final int SESSION_EXPIRATE = 300;

    @PostMapping(value = "/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password
                        , Map<String,Object> map, HttpSession session) {
        if (!StringUtils.isEmpty(username) && "123456".equals(password)) {
            //将会话信息放在session中
            session.setAttribute("loginUser",username );
            session.setMaxInactiveInterval(SESSION_EXPIRATE);
            //成功登录，防止表单重复提交，进行页面重定向
            return "redirect:/loginSuccess.html";
        }else {
            map.put("msg", "密码错误！");
            return "login";
        }
    }

}
