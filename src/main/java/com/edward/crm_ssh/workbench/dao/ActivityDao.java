package com.edward.crm_ssh.workbench.dao;

import com.edward.crm_ssh.workbench.domain.Activity;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.workbench.dao
 * @ClassName: ActivityDao
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/10 20:54
 * @Version: 1.0
 */
public interface ActivityDao {
    int getTotalByCondition(Map<String, Object> map);

    List<Activity> getActivityListByCondition(Map<String, Object> map);

    int save(Activity a);

    int update(Activity a);

    Activity getActivityById(String id);

    Activity detail(String id);

}
