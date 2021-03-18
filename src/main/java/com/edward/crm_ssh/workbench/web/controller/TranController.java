package com.edward.crm_ssh.workbench.web.controller;

import com.edward.crm_ssh.settings.domain.User;
import com.edward.crm_ssh.settings.service.UserService;
import com.edward.crm_ssh.utils.DateTimeUtil;
import com.edward.crm_ssh.utils.PrintJson;
import com.edward.crm_ssh.utils.UUIDUtil;
import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.domain.*;
import com.edward.crm_ssh.workbench.service.ActivityService;
import com.edward.crm_ssh.workbench.service.ContactsService;
import com.edward.crm_ssh.workbench.service.CustomerService;
import com.edward.crm_ssh.workbench.service.TranService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.ServletContext;
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
    @Qualifier("ActivityServiceImpl")
    private ActivityService as;

    @Autowired
    @Qualifier("TranServiceImpl")
    private TranService ts;

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService us;

    @Autowired
    @Qualifier("CustomerServiceImpl")
    private CustomerService cs;

    @Autowired
    @Qualifier("ContactsServiceImpl")
    private ContactsService contactsService;

    @RequestMapping(value = "/pageList.do", method = RequestMethod.GET)
    private void pageList(String contactsName, String customerName, Tran tran, String pageNo, String pageSize, HttpServletRequest request, HttpServletResponse response) {
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

        map.put("owner", tran.getOwner());
        map.put("name", tran.getName());
        map.put("source", tran.getSource());
        map.put("type", tran.getType());
        map.put("stage", tran.getStage());
        map.put("contactsName", contactsName);
        map.put("customerName", customerName);
        map.put("skipCount", skipCount);
        map.put("pageSize", pageSize1);

        PaginationVO<Tran> pageList = ts.pageList(map);

        PrintJson.printJsonObj(response, pageList);

    }

    @RequestMapping(value = "/add.do", method = RequestMethod.GET)
    private ModelAndView add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入到跳转交易添加页操作");

        ModelAndView mv = new ModelAndView();


        List<User> uList = us.getUserList();
        mv.addObject("uList", uList);

        mv.setViewName("forward:/workbench/transaction/save.jsp");
        return mv;
    }

    @RequestMapping(value = "/getCustomerName.do", method = RequestMethod.GET)
    private void getCustomerName(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("取得客户名称列表，按照客户名称进行模糊查询");

        String name = request.getParameter("name");

        List<String> list = cs.getCustomerName(name);

        PrintJson.printJsonObj(response, list);
    }

    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    private void save(Tran tran, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("执行添加交易的操作");

        System.out.println(tran.getCustomerId());
        System.out.println(tran.getContactsId());

        String id = UUIDUtil.getUUID();
        String owner = request.getParameter("owner");
        String money = request.getParameter("money");
        String name = request.getParameter("name");
        String expectedDate = request.getParameter("expectedDate");
        //此时只有customerName，没有customerId
        String customerName = request.getParameter("customerName");
        String stage = request.getParameter("stage");
        String type = request.getParameter("type");
        String source = request.getParameter("source");
        String activityId = request.getParameter("activityId");
        String contactsId = request.getParameter("contactsId");
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User) request.getSession().getAttribute("user")).getName();
        String description = request.getParameter("description");
        String contactSummary = request.getParameter("contactSummary");
        String nextContactTime = request.getParameter("nextContactTime");

        Tran t = new Tran();
        t.setId(id);
        t.setOwner(owner);
        t.setMoney(money);
        t.setName(name);
        t.setExpectedDate(expectedDate);
        //注意，此时还是name，没有取得id

        t.setStage(stage);
        t.setType(type);
        t.setSource(source);
        t.setActivityId(activityId);
        t.setContactsId(contactsId);
        t.setCreateBy(createBy);
        t.setCreateTime(createTime);
        t.setDescription(description);
        t.setContactSummary(contactSummary);
        t.setNextContactTime(nextContactTime);


        //在这里需要按照名字操作
        boolean flag = ts.save(t, customerName);

        if (flag) {
            //跳转到列表页
            response.sendRedirect(request.getContextPath() + "/workbench/transaction/index.jsp");
        }


    }

    @RequestMapping(value = "/getActivityList.do", method = RequestMethod.GET)
    private void getActivityList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到tranController的getActivityList方法中");

        List<Activity> aList = as.getActivityList();

        PrintJson.printJsonObj(response, aList);
    }

    @RequestMapping(value = "/getContactsList.do", method = RequestMethod.GET)
    private void getContactsList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到tranController的getContactsList方法中");

        List<Contacts> cList = contactsService.getContactsList();

        PrintJson.printJsonObj(response, cList);
    }

    @RequestMapping(value = "/detail.do", method = RequestMethod.GET)
    private ModelAndView detail(String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("跳转到详细信息页");

        ModelAndView mv = new ModelAndView();

        Tran t = ts.detail(id);

        //处理可能性
        /*
            阶段和可能性之间对应关系
        * */

        //以下三种方式都可以
        //任选其一
        /*ServletContext applivation1 = this.getServletContext();
        ServletContext applivation2 = request.getServletContext();
        ServletContext applivation3 = this.getServletConfig().getServletContext();*/


        Map<String, String> pMap = (Map<String, String>) request.getServletContext().getAttribute("pMap");
        String possibility = pMap.get(t.getStage());
        t.setPossibility(possibility);

        mv.addObject("t", t);


        mv.setViewName("forward:/workbench/transaction/detail.jsp");


        //因为到了详细信息也，用request转发，如果在页面刷新，就是刷。do/而不是jsp
        //因为jsp本身没数据


        return mv;


    }

    @RequestMapping(value = "/getHistoryListByTranId.do", method = RequestMethod.GET)
    private void getHistoryListByTranId(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("根据交易id获取交易历史列表");

        String tranId = request.getParameter("tranId");

        List<TranHistory> thList = ts.getHistoryListByTranId(tranId);

        //添加一个可能性得属性
        //为什么在控制层添加数据，因为application这里有
        Map<String,String> pMap = (Map<String, String>) request.getServletContext().getAttribute("pMap");
        for(TranHistory tranHistory:thList)
        {
            //根据每一条交易历史
            //取出每一个阶段
            String stage = tranHistory.getStage();
            String possibility = pMap.get(stage);
            tranHistory.setPossibility(possibility);
        }

        PrintJson.printJsonObj(response,thList);


    }

    @RequestMapping(value = "/changeStage.do", method = RequestMethod.POST)
    private void changeStage(Tran t,HttpServletRequest request, HttpServletResponse response) {
        System.out.println("改变阶段");

        /*String id = request.getParameter("id");
        String stage = request.getParameter("stage");
        String money = request.getParameter("money");
        String expectedDate = request.getParameter("expectedDate");*/

        String editTime = DateTimeUtil.getSysTime();
        //创建人：当前登录用户
        String editBy = ((User)request.getSession().getAttribute("user")).getName();


        /*t.setId(id);
        t.setStage(stage);
        t.setMoney(money);
        t.setExpectedDate(expectedDate);*/
        t.setEditBy(editBy);
        t.setEditTime(editTime);

        Map<String,String> pMap = (Map<String, String>) request.getServletContext().getAttribute("pMap");
        String possibility = pMap.get(t.getStage());
        t.setPossibility(possibility);


        boolean flag = ts.changeStage(t);

        Map<String,Object> map = new HashMap<>();
        map.put("success",flag);
        map.put("t",t);

        PrintJson.printJsonObj(response,map);
    }

    @RequestMapping(value = "/saveRemark.do", method = RequestMethod.POST)
    private void saveRemark(HttpSession httpSession,TranRemark tr, HttpServletResponse response) {
        System.out.println("执行保存评论操作");

        String id = UUIDUtil.getUUID();
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User) httpSession.getAttribute("user")).getName();
        String editFlag = "0";

        tr.setId(id);
        tr.setCreateTime(createTime);
        tr.setCreateBy(createBy);
        tr.setEditFlag(editFlag);

        boolean flag = ts.saveRemark(tr);

        Map<String, Object> map = new HashMap<>();

        map.put("success", flag);
        map.put("tr", tr);

        PrintJson.printJsonObj(response, map);

    }

    @RequestMapping(value = "/deleteRemark.do", method = RequestMethod.POST)
    private void deleteRemark(String id, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行删除评论操作");


        boolean flag = ts.deleteRemark(id);

        PrintJson.printJsonFlag(response, flag);


    }

    @RequestMapping(value = "/updateRemark.do", method = RequestMethod.POST)
    private void updateRemark(TranRemark tr, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行更新评论操作");



        String editTime = DateTimeUtil.getSysTime();
        String editBy = ((User) httpSession.getAttribute("user")).getName();
        String editFlag = "1";

        tr.setEditFlag(editFlag);

        tr.setEditBy(editBy);
        tr.setEditTime(editTime);


        boolean flag = ts.updateRemark(tr);

        Map<String, Object> map = new HashMap<>();

        map.put("success", flag);
        map.put("tr", tr);

        PrintJson.printJsonObj(response, map);

    }

    @RequestMapping(value = "/getRemarkListByAid.do", method = RequestMethod.GET)
    private void getRemarkListByAid(String tranId, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行通过id获取评论列表");

        System.out.println("tranId:" + tranId);

        List<TranRemark> trList = ts.getRemarkListByAid(tranId);

        if (trList == null)
            System.out.println("空");
        else
            System.out.println("不为空");

        PrintJson.printJsonObj(response, trList);


    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    private void delete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到交易删除操作");

        String ids[] = request.getParameterValues("id");


        boolean flag = ts.delete(ids);

        //传到前端
        PrintJson.printJsonFlag(response, flag);


    }

    @RequestMapping(value = "/getCharts.do", method = RequestMethod.GET)
    private void getCharts(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("获取交易统计图表的数据");


        /*
            业务层需要返回：
                    total
                    dataList

                    通过map打包以上两项进行返回
        */

        Map<String,Object> map = ts.getCharts();

        PrintJson.printJsonObj(response,map);



    }
}
