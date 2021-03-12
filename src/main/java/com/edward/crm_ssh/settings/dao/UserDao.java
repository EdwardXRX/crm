package com.edward.crm_ssh.settings.dao;

import com.edward.crm_ssh.settings.domain.User;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.settings.dao
 * @ClassName: UserDao
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/10 18:45
 * @Version: 1.0
 */
public interface  UserDao {

    User login(Map<String, Object> map);

    List<User> getUserList();

    int updatePwd(Map<String, Object> map);
}
