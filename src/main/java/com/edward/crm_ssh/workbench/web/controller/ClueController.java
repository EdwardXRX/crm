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
        System.out.println("??????????????????????????????????????????????????????????????????+???????????????");

        //?????????????????????
        int pageNo1 = Integer.valueOf(pageNo);

        //???????????????
        int pageSize1 = Integer.valueOf(pageSize);

        //???????????????sql????????????
        //sql????????????????????????????????????
        //?????????????????????????????????
        int skipCount = (pageNo1 - 1) * pageSize1;

        //???????????????map
        //vo???????????????????????????
        //?????????????????????
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
        System.out.println("??????????????????");

        List<User> userList = us.getUserList();

        PrintJson.printJsonObj(response, userList);
    }

    @RequestMapping(value = "/save.do", method = RequestMethod.POST)
    private void save(HttpSession httpSession, Clue clue, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("??????????????????");

        //????????????????????????????????????id
        String id = UUIDUtil.getUUID();

        //????????????????????????????????????
        String createTime = DateTimeUtil.getSysTime();

        //????????????session?????????????????????
        String createBy = ((User) httpSession.getAttribute("user")).getName();

        //???????????????????????????????????????id,createBy,createTime???????????????springMVC????????????????????????
        clue.setId(id);
        clue.setCreateBy(createBy);
        clue.setCreateTime(createTime);

        boolean flag = cs.save(clue);

        PrintJson.printJsonFlag(response, flag);
    }

    @RequestMapping(value = "/getUserListAndClue.do", method = RequestMethod.GET)
    private void getUserListAndClue(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("?????????????????????????????????????????????????????????????????????");

        String id = request.getParameter("id");


        Map<String, Object> map = cs.getUserListAndClue(id);


        PrintJson.printJsonObj(response, map);
    }

    @RequestMapping(value = "/update.do", method = RequestMethod.POST)
    private void update(HttpSession httpSession, Clue clue, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("????????????????????????");


        //??????????????????????????????
        clue.setEditBy(((User) httpSession.getAttribute("user")).getName());
        clue.setEditTime(DateTimeUtil.getSysTime());

        Boolean flag = cs.update(clue);

        PrintJson.printJsonFlag(response, flag);

    }

    @RequestMapping(value = "/deleteIds.do", method = RequestMethod.POST)
    private void deleteIds(String[] id, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("????????????????????????");

        boolean flag = cs.deleteIds(id);

        //????????????
        PrintJson.printJsonFlag(response, flag);
    }

    @RequestMapping(value = "/detail.do", method = RequestMethod.GET)
    private ModelAndView detail(String id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("??????????????????");

        ModelAndView mv = new ModelAndView();

        Clue clue = cs.detail(id);

        mv.addObject("clue", clue);
        mv.setViewName("forward:/workbench/clue/detail.jsp");

        return mv;
    }

    @RequestMapping(value = "/getActivityListByClueId.do", method = RequestMethod.GET)
    private void getActivityListByClueId(@RequestParam(value = "id") String clueId, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("?????????????????????id?????????????????????????????????");

        List<Activity> aList = as.getActivityListByClueId(clueId);

        PrintJson.printJsonObj(response, aList);
    }

    @RequestMapping(value = "/unbund.do", method = RequestMethod.POST)
    private void unbund(String id, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("?????????????????????????????????");

        boolean flag = cs.unbund(id);

        PrintJson.printJsonFlag(response, flag);


    }

    @RequestMapping(value = "/bundActivity.do", method = RequestMethod.POST)
    private void bundActivity(String clueId, String activityIds, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("???????????????????????????");

        String activityId[] = activityIds.split(",");

        //????????????
        for (int i = 0; i < activityId.length; i++) {
            System.out.println(i + ":" + activityId[i]);
        }

        //????????????map
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
        System.out.println("????????????????????????");

        Map<String, String> map = new HashMap<>();
        map.put("clueId", clueId);
        map.put("aname", aname);

        List<Activity> aList = as.getActivityListByNameAndNotByClueId(map);

        PrintJson.printJsonObj(response, aList);
    }

    //??????????????????????????????????????????
    @RequestMapping(value = "/convert.do", method = RequestMethod.GET)
    private void convert(String clueId, String flag, String money, String name, String stage, String activityId, String expectedDate, HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println("????????????????????????");
        //????????????id
        /*String clueId = request.getParameter("clueId");*/
        String createBy = ((User) (request.getSession().getAttribute("user"))).getName();

        //String flag = request.getParameter("flag");

        Tran t = null;
        //???????????????????????????
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
         * ??????????????????????????????
         * 1. clueId
         * 2. t?????? ??????????????????????????????
         * ??????t????????????
         *
         * */


        boolean flag1 = cs.convert(clueId, t, createBy);

        if (flag1) {
            response.sendRedirect(request.getContextPath() + "/workbench/clue/index.jsp");
        }


    }

    @RequestMapping(value = "/getActivityListByName.do", method = RequestMethod.GET)
    private void getActivityListByName(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("??????????????????");
        String aname = request.getParameter("aname");


        List<Activity> aList = as.getActivityListByName(aname);

        PrintJson.printJsonObj(response, aList);

    }
}
