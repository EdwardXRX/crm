package com.edward.crm_ssh.settings.service.impl;

import com.edward.crm_ssh.exception.LoginException;
import com.edward.crm_ssh.exception.UpdatePwdException;
import com.edward.crm_ssh.settings.dao.UserDao;
import com.edward.crm_ssh.settings.domain.User;
import com.edward.crm_ssh.settings.service.UserService;
import com.edward.crm_ssh.utils.DateTimeUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.settings.service.impl
 * @ClassName: UserServiceImpl
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/10 18:43
 * @Version: 1.0
 */

@Service("UserServiceImpl")
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public User login(String loginAct, String loginPwd, String ip) throws LoginException {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("loginAct", loginAct);
        map.put("loginPwd", loginPwd);

        User user = userDao.login(map);

        if (user == null) {
            throw new LoginException("账号密码错误");
        }

        String expireTime = user.getExpireTime();
        String currentTime = DateTimeUtil.getSysTime();

        //判断失效时间
        //小于0 ，说明账号失效
        if (expireTime.compareTo(currentTime) < 0) {
            throw new LoginException("账号已失效");
        }

        //判断锁定
        String lockState = user.getLockState();
        if ("0".equals(lockState)) {
            throw new LoginException("账号已锁定");
        }


        //判断ip地址
        String allowIps = user.getAllowIps();
        if (!allowIps.contains(ip)) {
            throw new LoginException("IP受限！");
        }

        //如果程序成功执行到本行
        //说明账号密码正常
        return user;
    }

    @Override
    public List<User> getUserList() {
        List<User> userList = userDao.getUserList();
        return userList;

    }

    @Override
    public Boolean updatePwd(String id, String loginAct, String oldPwd, String newPwd) throws UpdatePwdException {

        Map<String, Object> map = new HashMap<String, Object>();

        map.put("loginAct", loginAct);
        map.put("loginPwd", oldPwd);

        User user = userDao.login(map);

        if (user == null) {
            throw new UpdatePwdException("原始密码错误");
        }

        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("id", id);
        map1.put("newPwd", newPwd);
        int nums = userDao.updatePwd(map1);

        if (nums != 1) {
            throw new UpdatePwdException("修改密码失败");
        }
        return true;
    }
}
