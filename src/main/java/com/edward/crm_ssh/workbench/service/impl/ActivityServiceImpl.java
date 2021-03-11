package com.edward.crm_ssh.workbench.service.impl;

import com.edward.crm_ssh.settings.dao.UserDao;
import com.edward.crm_ssh.settings.domain.User;
import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.dao.ActivityDao;
import com.edward.crm_ssh.workbench.domain.Activity;
import com.edward.crm_ssh.workbench.service.ActivityService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.workbench.service.impl
 * @ClassName: ActivityServiceImpl
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/10 20:53
 * @Version: 1.0
 */

@Service("ActivityServiceImpl")
public class ActivityServiceImpl implements ActivityService {
    @Override
    public Activity detail(String id) {

        Activity a = activityDao.detail(id);

        return a;
    }

    @Resource
    private ActivityDao activityDao;

    @Resource
    private UserDao userDao;


    @Override
    public PaginationVO<Activity> pageList(Map<String, Object> map) {
        System.out.println("进入到ActivityServiceImpl文件中得pageList中");
        //取得total
        int total = activityDao.getTotalByCondition(map);

        System.out.println("total:" + total);

        //取得dataList
        List<Activity> dataList = activityDao.getActivityListByCondition(map);

        if(dataList == null){
            System.out.println("dataList为空");
        }
        else System.out.println("dataList不为空");
        //封装成vo

        PaginationVO<Activity> vo = new PaginationVO<Activity>();

        vo.setTotal(total);
        vo.setDataList(dataList);

        //返回

        return vo;
    }

    @Override
    public boolean save(Activity a) {
        int count  = activityDao.save(a);

        boolean flag = true;
        if(count != 1)
        {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean update(Activity a) {

        int count = activityDao.update(a);

        boolean flag = true;
        if(count != 1)
        {
            flag = false;
        }
        return flag;
    }

    @Override
    public Map<String, Object> getUserListAndActivity(String id) {

        //取uList
        List<User> uList = userDao.getUserList();

        //取得单条活动通过id值
        Activity a = activityDao.getActivityById(id);

        //将uList打包进map
        Map<String,Object> map = new HashMap<>();

        map.put("uList",uList);
        map.put("a",a);

        //返回map

        return map;
    }


}
