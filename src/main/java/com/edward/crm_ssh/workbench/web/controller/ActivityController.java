package com.edward.crm_ssh.workbench.web.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.edward.crm_ssh.settings.domain.User;
import com.edward.crm_ssh.settings.service.UserService;
import com.edward.crm_ssh.utils.DateTimeUtil;
import com.edward.crm_ssh.utils.PrintJson;
import com.edward.crm_ssh.utils.UUIDUtil;
import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.dao.ActivityDao;
import com.edward.crm_ssh.workbench.domain.Activity;
import com.edward.crm_ssh.workbench.domain.ActivityRemark;
import com.edward.crm_ssh.workbench.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import javax.annotation.Resource;
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
 * @ClassName: ActivityController
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/10 20:39
 * @Version: 1.0
 */

@Controller
@RequestMapping("/workbench/activity")
public class ActivityController {



    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService userService;

    @Autowired
    @Qualifier("ActivityServiceImpl")
    private ActivityService as;


    @RequestMapping(value = "/getUserList.do", method = RequestMethod.GET)
    private void getUserList(HttpServletResponse response) {
        System.out.println("取得用户信息表");

        List<User> userList = userService.getUserList();

        if (userList == null)
            System.out.println("空");
        else
            System.out.println("有");

        PrintJson.printJsonObj(response, userList);
    }

    @RequestMapping(value = "/pageList.do", method = RequestMethod.GET)
    private void pageList(HttpServletResponse response, String name, String owner, String startDate, String endDate, String pageNo, String pageSize) {
        System.out.println("进入到查询市场活动信息的列表的操作（结合条件查询+分页查询）");

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

        System.out.println("skipCount:" + skipCount);
        System.out.println("endDate:" + endDate);
        System.out.println("startDate:" + startDate);
        System.out.println("owner:" + owner);

        //打包成一个map
        //vo，是给前端传送数据
        //不是前端给后端
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("owner", owner);
        map.put("startDate", startDate);
        map.put("endDate", endDate);
        map.put("skipCount", skipCount);
        map.put("pageSize", pageSize1);



        PaginationVO<Activity> pageList = as.pageList(map);

        PrintJson.printJsonObj(response, pageList);
    }

    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    private void save(HttpSession httpSession, HttpServletResponse response, Activity activity) {

        System.out.println("添加活动信息页面");

        //工具类，获取随机id
        String id = UUIDUtil.getUUID();

        //工具类，获取当前系统时间
        String createTime = DateTimeUtil.getSysTime();

        //从session中获得当前登录用户的名字
        String createBy = ((User) httpSession.getAttribute("user")).getName();

        activity.setId(id);

        activity.setCreateTime(createTime);
        activity.setCreateBy(createBy);

        boolean flag = as.save(activity);

        PrintJson.printJsonFlag(response, flag);
    }

    @RequestMapping(value = "/getUserListAndActivity.do", method = RequestMethod.GET)
    private void getUserListAndActivity(String id, HttpServletResponse response) {
        System.out.println("执行市场活动修改，根据id得到活动，获得一个uList");

        Map<String, Object> map = as.getUserListAndActivity(id);

        PrintJson.printJsonObj(response, map);
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    private void update(Activity a, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response, String id) {
        System.out.println("执行市场活动修改操作");
        System.out.println("直接从前端自动封装成类，然后补上编辑时间和编辑人就行");

        //工具类获取现在时间
        String editTime = DateTimeUtil.getSysTime();
        //创建人：当前登录用户
        String editBy = ((User) httpSession.getAttribute("user")).getName();

        a.setEditTime(editTime);
        a.setEditBy(editBy);

        boolean flag = as.update(a);

        PrintJson.printJsonFlag(response, flag);
    }

    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    private void delete(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到活动删除操作");

        String ids[] = request.getParameterValues("id");


        boolean flag = as.delete(ids);

        //传到前端
        PrintJson.printJsonFlag(response, flag);


    }

    @RequestMapping(value = "/detail.do", method = RequestMethod.GET)
    private void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("进入细节");

        String id = request.getParameter("id");

        Activity a = as.detail(id);


        request.setAttribute("a", a);

        request.getRequestDispatcher("/workbench/activity/detail.jsp").forward(request, response);

    }

    @RequestMapping(value = "/getRemarkListByAid.do", method = RequestMethod.GET)
    private void getRemarkListByAid(String activityId, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行通过id获取评论列表");

        //String activityId = request.getParameter("activityId");

        System.out.println("activityId:" + activityId);

        List<ActivityRemark> arList = as.getRemarkListByAid(activityId);

        if (arList == null)
            System.out.println("空");
        else
            System.out.println("不为空");

        PrintJson.printJsonObj(response, arList);


    }

    @RequestMapping(value = "/deleteRemark.do", method = RequestMethod.POST)
    private void deleteRemark(String id, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行删除评论操作");

        //String id = request.getParameter("id");

        boolean flag = as.deleteRemark(id);

        PrintJson.printJsonFlag(response, flag);


    }

    @RequestMapping(value = "/saveRemark.do", method = RequestMethod.POST)
    private void saveRemark(HttpSession httpSession, ActivityRemark ar, HttpServletResponse response) {
        System.out.println("执行保存评论操作");

        String id = UUIDUtil.getUUID();
        String createTime = DateTimeUtil.getSysTime();
        String createBy = ((User) httpSession.getAttribute("user")).getName();
        String editFlag = "0";

        ar.setId(id);
        ar.setCreateTime(createTime);
        ar.setCreateBy(createBy);
        ar.setEditFlag(editFlag);

        boolean flag = as.saveRemark(ar);

        Map<String, Object> map = new HashMap<>();

        map.put("success", flag);
        map.put("ar", ar);

        PrintJson.printJsonObj(response, map);

    }

    @RequestMapping(value = "/updateRemark.do", method = RequestMethod.POST)
    private void updateRemark(ActivityRemark ar, HttpSession httpSession, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行更新评论操作");

/*        String id = request.getParameter("id");
        String noteContent = request.getParameter("noteContent");*/

        String editTime = DateTimeUtil.getSysTime();
        String editBy = ((User) httpSession.getAttribute("user")).getName();
        String editFlag = "1";

        /*   ActivityRemark ar = new ActivityRemark();*/
        ar.setEditFlag(editFlag);
       /* ar.setId(id);
        ar.setNoteContent(noteContent);*/
        ar.setEditBy(editBy);
        ar.setEditTime(editTime);


        boolean flag = as.updateRemark(ar);

        Map<String, Object> map = new HashMap<>();

        map.put("success", flag);
        map.put("ar", ar);

        PrintJson.printJsonObj(response, map);

    }

}
