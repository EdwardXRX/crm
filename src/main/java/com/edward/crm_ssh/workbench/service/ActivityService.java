package com.edward.crm_ssh.workbench.service;

import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.domain.Activity;
import com.edward.crm_ssh.workbench.domain.ActivityRemark;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.workbench.service
 * @ClassName: ActivityService
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/10 20:51
 * @Version: 1.0
 */


public interface ActivityService {

    PaginationVO<Activity> pageList(Map<String, Object> map);

    boolean save(Activity a);

    Map<String, Object> getUserListAndActivity(String id);

    boolean update(Activity a);


    Activity detail(String id);

    List<ActivityRemark> getRemarkListByAid(String activityId);

    boolean deleteRemark(String id);

    boolean saveRemark(ActivityRemark ar);

    boolean updateRemark(ActivityRemark ar);

    boolean delete(String[] ids);

}
