package com.edward.crm_ssh.settings.web.controller;

import com.edward.crm_ssh.settings.domain.User;
import com.edward.crm_ssh.settings.service.UserService;
import com.edward.crm_ssh.utils.MD5Util;
import com.edward.crm_ssh.utils.PrintJson;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.settings.web.controller
 * @ClassName: UserController
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/10 17:34
 * @Version: 1.0
 */

@Controller
public class UserController {

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService us;


    @RequestMapping(value = "/settings/user/login.do",method = RequestMethod.POST)
    private void login(HttpServletRequest request, HttpServletResponse response,String loginAct,String loginPwd){

        //转化成MD5加密形式
        loginPwd = MD5Util.getMD5(loginPwd);

        //接受浏览器的id地址
        String ip = request.getRemoteAddr();
        System.out.println("---------ip"+ip);

        System.out.println("111111111111");

        try {

            //一旦在login出问题，会直接跳转到catch块中
            User user = us.login(loginAct,loginPwd,ip);

            request.getSession().setAttribute("user",user);

            if(user == null){
                System.out.println("user为空");
            }else
                System.out.println("user不为空");
            //如果程序运行到这儿，说明上面的取得User类没有出问题
            PrintJson.printJsonFlag(response,true);

        }catch (Exception e)
        {
            e.printStackTrace();

            //直接报错
            String msg = e.getMessage();
            System.out.println("-----------msg"+msg);

            Map<String,Object> map = new HashMap<String,Object>();
            map.put("success",false);
            map.put("msg",msg);
            PrintJson.printJsonObj(response,map);
        }
    }

}
