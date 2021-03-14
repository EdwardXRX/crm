package com.edward.crm_ssh.workbench.web.controller;

import com.edward.crm_ssh.settings.domain.User;
import com.edward.crm_ssh.settings.service.UserService;
import com.edward.crm_ssh.utils.DateTimeUtil;
import com.edward.crm_ssh.utils.PrintJson;
import com.edward.crm_ssh.utils.UUIDUtil;
import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.domain.Clue;
import com.edward.crm_ssh.workbench.service.ClueService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.java2d.pipe.SpanClipRenderer;

import javax.annotation.Resource;
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


    @RequestMapping(value = "/pageList.do", method = RequestMethod.GET)
    private void pageList(Clue clue, String pageNo, String pageSize, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("进入到查询线索信息的列表的操作（结合条件查询+分页查询）");

/*      String fullname = request.getParameter("fullname");
        String company = request.getParameter("company");
        String owner = request.getParameter("owner");
        String source = request.getParameter("source");
        String state = request.getParameter("state");
        String phone = request.getParameter("phone");
        String mphone = request.getParameter("mphone");
        String pageNostr = request.getParameter("pageNo");*/
        System.out.println("-----------------------");

        System.out.println("fullname:" + clue.getFullname());
        System.out.println("company:" + clue.getCompany());
        System.out.println("owner:" + clue.getOwner());
        System.out.println("source:" + clue.getSource());
        System.out.println("state:" + clue.getState());
        System.out.println("phone:" + clue.getPhone());
        System.out.println("mphone:" + clue.getMphone());

        System.out.println("-----------------------");


        //计算略过的数量
        int pageNo1 = Integer.valueOf(pageNo);

        System.out.println("pageNo1:" + pageNo1);

        /*String pageSizestr = request.getParameter("pageSize");*/


        //每页的数字
        int pageSize1 = Integer.valueOf(pageSize);

        System.out.println("pageSize1:" + pageSize1);

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
    private void deleteIds(String[] id,HttpServletRequest request, HttpServletResponse response) {
        System.out.println("执行线索删除操作");


     /*   String ids[] = request.getParameterValues("id");
        for (int i = 0; i < ids.length; i++) {
            System.out.println(ids[i]);
        }*/


        boolean flag = cs.deleteIds(id);

        //传到前端
        PrintJson.printJsonFlag(response, flag);


    }
}
