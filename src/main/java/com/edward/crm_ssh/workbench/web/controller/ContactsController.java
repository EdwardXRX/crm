package com.edward.crm_ssh.workbench.web.controller;

import com.edward.crm_ssh.utils.PrintJson;
import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.domain.Activity;
import com.edward.crm_ssh.workbench.domain.Contacts;
import com.edward.crm_ssh.workbench.service.ContactsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.workbench.web.controller
 * @ClassName: ContactsController
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/17 18:51
 * @Version: 1.0
 */

@Controller
@RequestMapping("/workbench/contacts")
public class ContactsController {

    @Autowired
    @Qualifier("ContactsServiceImpl")
    private ContactsService contactsService;


    @RequestMapping(value = "/pageList.do", method = RequestMethod.GET)
    private void pageList(HttpServletResponse response, String fullname, String owner, String source, String birth, String customerName, String pageNo, String pageSize) {
        System.out.println("进入到联系人信息的列表的操作（结合条件查询+分页查询）");

        //计算略过的数量
        int pageNo1 = Integer.valueOf(pageNo);

        System.out.println("pageNo:" + pageNo);

        //每页的数字
        int pageSize1 = Integer.valueOf(pageSize);

        System.out.println("pageSize:" + pageSize);

        //这是应用在sql语句中的
        //sql语句，第一个位掠过的数量
        //第二个为每页查询的数量
        int skipCount = (pageNo1 - 1) * pageSize1;


        //打包成一个map
        //vo，是给前端传送数据
        //不是前端给后端
        Map<String, Object> map = new HashMap<>();
        map.put("fullname", fullname);
        map.put("owner", owner);
        map.put("customerName", customerName);
        map.put("source", source);
        map.put("birth", birth);
        map.put("skipCount", skipCount);
        map.put("pageSize", pageSize1);


        PaginationVO<Contacts> pageList = contactsService.pageList(map);

        PrintJson.printJsonObj(response, pageList);
    }

}
