package com.edward.crm_ssh.workbench.web.controller;

import com.edward.crm_ssh.utils.PrintJson;
import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.domain.Clue;
import com.edward.crm_ssh.workbench.domain.Tran;
import com.edward.crm_ssh.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.workbench.web.controller
 * @ClassName: TranController
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/14 22:11
 * @Version: 1.0
 */
@Controller
@RequestMapping("/workbench/tran")
public class TranController {

    @Autowired
    @Qualifier("TranServiceImpl")
    private TranService ts;

    @RequestMapping(value = "/pageList.do", method = RequestMethod.GET)
    private void pageList(String contactsName,String customerName,Tran tran, String pageNo, String pageSize, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入交易页面列表方法");

        //计算略过的数量
        int pageNo1 = Integer.valueOf(pageNo);

        //每页的数字
        int pageSize1 = Integer.valueOf(pageSize);

        //这是应用在sql语句中的
        //sql语句，第一个位掠过的数量
        //第二个为每页查询的数量
        int skipCount = (pageNo1 - 1) * pageSize1;

        Map<String, Object> map = new HashMap<>();

        map.put("owner",tran.getOwner());
        map.put("name",tran.getName());
        map.put("source",tran.getSource());
        map.put("type",tran.getType());
        map.put("stage",tran.getStage());
        map.put("contactsName",contactsName);
        map.put("customerName",customerName);
        map.put("skipCount",skipCount);
        map.put("pageSize", pageSize1);

        PaginationVO<Tran> pageList = ts.pageList(map);

        PrintJson.printJsonObj(response, pageList);

    }


}
