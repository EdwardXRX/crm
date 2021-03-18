package com.edward.crm_ssh.workbench.web.controller;

import com.edward.crm_ssh.settings.domain.User;
import com.edward.crm_ssh.settings.service.UserService;
import com.edward.crm_ssh.utils.DateTimeUtil;
import com.edward.crm_ssh.utils.PrintJson;
import com.edward.crm_ssh.utils.UUIDUtil;
import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.domain.*;
import com.edward.crm_ssh.workbench.service.CustomerService;
import com.edward.crm_ssh.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.workbench.web.controller
 * @ClassName: CustomerController
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/16 20:42
 * @Version: 1.0
 */

@Controller
@RequestMapping("/workbench/customer")
public class CustomerController {

    @Autowired
    @Qualifier("CustomerServiceImpl")
    private CustomerService customerService;

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("TranServiceImpl")
    private TranService tranService;

    @RequestMapping(value = "/pageList.do", method = RequestMethod.GET)
    private void pageList(HttpServletResponse response, String name, String owner, String phone, String website, String pageNo, String pageSize) {
        System.out.println("进入到查询客户信息的列表的操作（结合条件查询+分页查询）");

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
        map.put("name", name);
        map.put("owner", owner);
        map.put("phone", phone);
        map.put("website", website);
        map.put("skipCount", skipCount);
        map.put("pageSize", pageSize1);


        PaginationVO<Customer> pageList = customerService.pageList(map);

        PrintJson.printJsonObj(response, pageList);
    }

    @RequestMapping(value = "/getUserList.do", method = RequestMethod.GET)
    private void getUserList(HttpServletResponse response) {
        System.out.println("取得用户信息表");

        List<User> userList = userService.getUserList();

        PrintJson.printJsonObj(response, userList);
    }

    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    private void save(HttpSession httpSession, HttpServletResponse response, Customer customer) {

        System.out.println("添加活动信息页面");

        //工具类，获取随机id
        String id = UUIDUtil.getUUID();

        //工具类，获取当前系统时间
        String createTime = DateTimeUtil.getSysTime();

        //从session中获得当前登录用户的名字
        String createBy = ((User) httpSession.getAttribute("user")).getName();

        customer.setId(id);
        customer.setCreateTime(createTime);
        customer.setCreateBy(createBy);

        boolean flag = customerService.save(customer);

        PrintJson.printJsonFlag(response, flag);
    }

    @RequestMapping(value = "/detail.do", method = RequestMethod.GET)
    private ModelAndView detail(String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入客户细节");

        ModelAndView mv = new ModelAndView();

        Customer c = customerService.detail(id);

        mv.addObject("c", c);

        mv.setViewName("forward:/workbench/customer/detail.jsp");

        return mv;
    }

    @RequestMapping(value = "/getRemarkListByCid.do", method = RequestMethod.GET)
    private void getRemarkListByCid(String customerId, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行通过id获取评论列表");

        List<CustomerRemark> crList = customerService.getRemarkListByCid(customerId);

        PrintJson.printJsonObj(response, crList);

    }

    @RequestMapping(value = "/saveRemark.do", method = RequestMethod.POST)
    private void saveRemark(HttpSession httpSession, CustomerRemark cr, HttpServletResponse response) {
        System.out.println("执行保存评论操作");

        String id = UUIDUtil.getUUID();
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User) httpSession.getAttribute("user")).getName();
        String editFlag = "0";

        cr.setId(id);
        cr.setCreateTime(createTime);
        cr.setCreateBy(createBy);
        cr.setEditFlag(editFlag);

        boolean flag = customerService.saveRemark(cr);

        Map<String, Object> map = new HashMap<>();

        map.put("success", flag);
        map.put("cr", cr);

        PrintJson.printJsonObj(response, map);

    }

    @RequestMapping(value = "/updateRemark.do", method = RequestMethod.POST)
    private void updateRemark(CustomerRemark cr, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行更新评论操作");

        String editTime = DateTimeUtil.getSysTime();
        String editBy = ((User) httpSession.getAttribute("user")).getName();
        String editFlag = "1";

        cr.setEditFlag(editFlag);

        cr.setEditBy(editBy);
        cr.setEditTime(editTime);


        boolean flag = customerService.updateRemark(cr);

        Map<String, Object> map = new HashMap<>();

        map.put("success", flag);
        map.put("cr", cr);

        PrintJson.printJsonObj(response, map);

    }

    @RequestMapping(value = "/deleteRemark.do", method = RequestMethod.POST)
    private void deleteRemark(String id, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行删除评论操作");


        boolean flag = customerService.deleteRemark(id);

        PrintJson.printJsonFlag(response, flag);


    }

    @RequestMapping(value = "/getTranList.do", method = RequestMethod.GET)
    private void getTranList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("获取交易列表");

        List<Tran> tranList = tranService.getTranList();

        PrintJson.printJsonObj(response, tranList);
    }

    @RequestMapping(value = "/deleteTran.do", method = RequestMethod.POST)
    private void deleteTran(String id, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行删除交易操作");

        String[] ids = new String[1];
        ids[0] = id;

        boolean flag = tranService.delete(ids);

        PrintJson.printJsonFlag(response, flag);
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    private void delete(String[] customerIds, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到客户删除操作");

        boolean flag = true;

        //传入的是多个customerId的值,我们要根据这些id把所有交易，交易评论，交易历史全部删除
        //然后客户和交易是 一对多的关系
        //交易和交易历史，交易评论也是一对多的关系
        //说明我们要删除客户的话，删除的东西非常多
        for (int i = 0; i < customerIds.length; i++) {

            String[] tranIds = tranService.getTranIdsByCustomerId(customerIds[i]);

            if (tranService.delete(tranIds) == false) {
                flag = false;
                break;
            }
        }
        /*上面删除完相关的交易之后，开始删除客户*/

        //删除客户
        boolean flag2 = customerService.delete(customerIds);

        System.out.println("flag" + flag);
        System.out.println("flag2" + flag2);

        //传到前端
        PrintJson.printJsonFlag(response, flag && flag2);
    }
}
