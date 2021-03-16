package com.edward.crm_ssh.workbench.service.impl;

import com.edward.crm_ssh.settings.dao.UserDao;
import com.edward.crm_ssh.settings.domain.User;
import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.dao.ActivityDao;
import com.edward.crm_ssh.workbench.dao.ActivityRemarkDao;
import com.edward.crm_ssh.workbench.domain.Activity;
import com.edward.crm_ssh.workbench.domain.ActivityRemark;
import com.edward.crm_ssh.workbench.service.ActivityService;
import org.springframework.stereotype.Service;

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



    @Resource
    private ActivityDao activityDao;

    @Resource
    private UserDao userDao;

    @Resource
    private ActivityRemarkDao activityRemarkDao;




    @Override
    public List<Activity> getActivityListByName(String aname) {

        List<Activity> aList = activityDao.getActivityListByName(aname);


        return aList;
    }

    @Override
    public List<Activity> getActivityList() {

        List<Activity> aList = activityDao.getActivityList();

        return aList;
    }

    @Override
    public Activity detail(String id) {

        Activity a = activityDao.detail(id);

        return a;
    }

    @Override
    public PaginationVO<Activity> pageList(Map<String, Object> map) {
        System.out.println("进入到ActivityServiceImpl文件中得pageList中");
        //取得total
        int total = activityDao.getTotalByCondition(map);

        System.out.println("total:" + total);

        //取得dataList
        List<Activity> dataList = activityDao.getActivityListByCondition(map);

        if (dataList == null) {
            System.out.println("dataList为空");
        } else System.out.println("dataList不为空");
        //封装成vo

        PaginationVO<Activity> vo = new PaginationVO<Activity>();

        vo.setTotal(total);
        vo.setDataList(dataList);

        //返回

        return vo;
    }

    @Override
    public boolean save(Activity a) {
        int count = activityDao.save(a);

        boolean flag = true;
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public boolean update(Activity a) {

        int count = activityDao.update(a);

        boolean flag = true;
        if (count != 1) {
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
        Map<String, Object> map = new HashMap<>();

        map.put("uList", uList);
        map.put("a", a);

        //返回map

        return map;
    }

    @Override
    public List<ActivityRemark> getRemarkListByAid(String activityId) {

        List<ActivityRemark> arList = activityRemarkDao.getRemarkListByAid(activityId);
        return arList;
    }

    @Override
    public boolean deleteRemark(String id) {

        int count = activityRemarkDao.deleteRemark(id);

        boolean flag = true;

        if (count == 1) {
            flag = true;
        } else
            flag = false;

        return flag;
    }

    @Override
    public boolean updateRemark(ActivityRemark ar) {
        int count = activityRemarkDao.updateRemark(ar);
        boolean flag = true;
        if (count != 1)
            flag = false;
        return flag;
    }

    @Override
    public boolean delete(String[] ids) {

        boolean flag = true;
        //首先查询我们需要删除的备注数量
        int count = activityRemarkDao.getCountByAids(ids);

        //然后再查询我们删除的备注条数
        int count2 = activityRemarkDao.deleteByAids(ids);
        //两者进行比较，如果成功之后再删除我们的真正要删除的活动记录

        if (count != count2) {
            System.out.println("不成功");
            flag = false;
        }

        int count3 = activityDao.delete(ids);

        if (count3 != ids.length) {
            System.out.println("删除活动失败");
            flag = false;
        }
        //返回一个标志位
        return flag;
    }

    @Override
    public List<Activity> getActivityListByClueId(String clueId) {

        List<Activity> aList = activityDao.getActivityListByClueId(clueId);

        return aList;
    }

    @Override
    public List<Activity> getActivityListByNameAndNotByClueId(Map<String, String> map) {


        List<Activity> aList = activityDao.getActivityListByNameAndNotByClueId(map);

        return aList;
    }

    @Override
    public boolean saveRemark(ActivityRemark ar) {
        boolean flag = true;

        int count = activityRemarkDao.saveRemark(ar);
        if (count != 1)
            flag = false;

        return flag;
    }
}
