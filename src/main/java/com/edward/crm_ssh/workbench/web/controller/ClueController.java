package com.edward.crm_ssh.workbench.web.controller;

import com.edward.crm_ssh.settings.domain.User;
import com.edward.crm_ssh.settings.service.UserService;
import com.edward.crm_ssh.utils.DateTimeUtil;
import com.edward.crm_ssh.utils.PrintJson;
import com.edward.crm_ssh.utils.UUIDUtil;
import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.domain.Activity;
import com.edward.crm_ssh.workbench.domain.Clue;
import com.edward.crm_ssh.workbench.domain.Tran;
import com.edward.crm_ssh.workbench.service.ActivityService;
import com.edward.crm_ssh.workbench.service.ClueService;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.workbench.web.controller
 * @ClassName: ClueController
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/12 13:53
 * @Version: 1.0
 */

@Controller
@RequestMapping("/workbench/clue")
public class ClueController {

    @Autowired
    @Qualifier("ClueServiceImpl")
    private ClueService cs;

    @Autowired
    @Qualifier("UserServiceImpl")
    private UserService us;

    @Autowired
    @Qualifier("ActivityServiceImpl")
    private ActivityService as;


    @RequestMapping(value = "/pageList.do", method = RequestMethod.GET)
    private void pageList(Clue clue, String pageNo, String pageSize, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到查询线索信息的列表的操作（结合条件查询+分页查询）");

        //计算略过的数量
        int pageNo1 = Integer.valueOf(pageNo);

        //每页的数字
        int pageSize1 = Integer.valueOf(pageSize);

        //这是应用在sql语句中的
        //sql语句，第一个位掠过的数量
        //第二个为每页查询的数量
        int skipCount = (pageNo1 - 1) * pageSize1;

        //打包成一个map
        //vo，是给前端传送数据
        //不是前端给后端
        Map<String, Object> map = new HashMap<>();
        map.put("fullname", clue.getFullname());
        map.put("state", clue.getState());
        map.put("source", clue.getSource());
        map.put("company", clue.getCompany());
        map.put("mphone", clue.getMphone());
        map.put("phone", clue.getPhone());
        map.put("owner", clue.getOwner());
        map.put("skipCount", skipCount);
        map.put("pageSize", pageSize1);

        PaginationVO<Clue> pageList = cs.pageList(map);

        PrintJson.printJsonObj(response, pageList);
    }

    @RequestMapping(value = "/getUserList.do", method = RequestMethod.GET)
    private void getUserList(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("获取用户列表");

        List<User> userList = us.getUserList();

        PrintJson.printJsonObj(response, userList);
    }

    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    private void save(HttpSession httpSession, Clue clue, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("保存线索用户");

        //工具类获取一个独一无二的id
        String id = UUIDUtil.getUUID();

        //工具类获取当前系统的时间
        String createTime = DateTimeUtil.getSysTime();

        //获取当前session中的登陆人姓名
        String createBy = ((User) httpSession.getAttribute("user")).getName();

        //直接将前端封装过来的类设置id,createBy,createTime。其他信息springMVC已经帮忙封装好了
        clue.setId(id);
        clue.setCreateBy(createBy);
        clue.setCreateTime(createTime);

        boolean flag = cs.save(clue);

        PrintJson.printJsonFlag(response, flag);
    }

    @RequestMapping(value = "/getUserListAndClue.do", method = RequestMethod.GET)
    private void getUserListAndClue(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("为修改模态窗口获取用户信息和当前修改的线索信息");

        String id = request.getParameter("id");


        Map<String, Object> map = cs.getUserListAndClue(id);


        PrintJson.printJsonObj(response, map);
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    private void update(HttpSession httpSession, Clue clue, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行修改线索操作");


        //设置编辑人和编辑时间
        clue.setEditBy(((User) httpSession.getAttribute("user")).getName());
        clue.setEditTime(DateTimeUtil.getSysTime());

        Boolean flag = cs.update(clue);

        PrintJson.printJsonFlag(response, flag);

    }

    @RequestMapping(value = "/deleteIds.do", method = RequestMethod.POST)
    private void deleteIds(String[] id, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行线索删除操作");

        boolean flag = cs.deleteIds(id);

        //传到前端
        PrintJson.printJsonFlag(response, flag);
    }

    @RequestMapping(value = "/detail.do", method = RequestMethod.GET)
    private ModelAndView detail(String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("进入线索细节");

        ModelAndView mv = new ModelAndView();

        Clue clue = cs.detail(id);

        mv.addObject("clue", clue);
        mv.setViewName("forward:/workbench/clue/detail.jsp");

        return mv;
    }

    @RequestMapping(value = "/getActivityListByClueId.do", method = RequestMethod.GET)
    private void getActivityListByClueId(@RequestParam(value = "id") String clueId, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到通过线索id查询关联的市场活动列表");

        List<Activity> aList = as.getActivityListByClueId(clueId);

        PrintJson.printJsonObj(response, aList);
    }

    @RequestMapping(value = "/unbund.do", method = RequestMethod.POST)
    private void unbund(String id, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入接触市场关联控制器");

        boolean flag = cs.unbund(id);

        PrintJson.printJsonFlag(response, flag);


    }

    @RequestMapping(value = "/bundActivity.do", method = RequestMethod.POST)
    private void bundActivity(String clueId, String activityIds, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行为线索绑定活动");

        String activityId[] = activityIds.split(",");

        //输出测试
        for (int i = 0; i < activityId.length; i++) {
            System.out.println(i + ":" + activityId[i]);
        }

        //集合存储map
        List<Map<String, String>> list = new ArrayList<>();

        for (int i = 0; i < activityId.length; i++) {
            Map<String, String> map = new HashMap<>();
            map.put("id", UUIDUtil.getUUID());
            map.put("clueId", clueId);
            map.put("activityId", activityId[i]);
            list.add(map);
        }

        boolean flag = cs.bundActivity(list);
        System.out.println("flag:" + flag);

        PrintJson.printJsonFlag(response, flag);


    }

    @RequestMapping(value = "/getActivityListByNameAndNotByClueId.do", method = RequestMethod.GET)
    private void getActivityListByNameAndNotByClueId(String clueId, String aname, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("线索关联模态窗口");

        Map<String, String> map = new HashMap<>();
        map.put("clueId", clueId);
        map.put("aname", aname);

        List<Activity> aList = as.getActivityListByNameAndNotByClueId(map);

        PrintJson.printJsonObj(response, aList);
    }

    //重点中得难点，难点中得最简单
    @RequestMapping(value = "/convert.do", method = RequestMethod.GET)
    private void convert(String clueId, String flag, String money, String name, String stage, String activityId, String expectedDate, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("执行线索转换操作");
        //获取线索id
        /*String clueId = request.getParameter("clueId");*/
        String createBy = ((User) (request.getSession().getAttribute("user"))).getName();

        //String flag = request.getParameter("flag");

        Tran t = null;
        //勾选了复选框得情况
        if ("a".equals(flag)) {
            t = new Tran();
            /*String money = request.getParameter("money");
            String name = request.getParameter("name");
            String stage = request.getParameter("stage");
            String activityId = request.getParameter("activityId");
            String expectedDate = request.getParameter("expectedDate");*/
            String id = UUIDUtil.getUUID();
            String createTime = DateTimeUtil.getSysTime();
            t.setId(id);
            t.setActivityId(activityId);
            t.setName(name);
            t.setStage(stage);
            t.setMoney(money);
            t.setExpectedDate(expectedDate);
            t.setCreateTime(createTime);
            t.setCreateBy(createBy);

        }

        /*
         * 为业务层传递的参数：
         * 1. clueId
         * 2. t对象 可能临时创建一笔交易
         * 当然t可能为空
         *
         * */


        boolean flag1 = cs.convert(clueId, t, createBy);

        if (flag1) {
            response.sendRedirect(request.getContextPath() + "/workbench/clue/index.jsp");
        }


    }

    @RequestMapping(value = "/getActivityListByName.do", method = RequestMethod.GET)
    private void getActivityListByName(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行搜索活动");
        String aname = request.getParameter("aname");


        List<Activity> aList = as.getActivityListByName(aname);

        PrintJson.printJsonObj(response, aList);

    }
}
