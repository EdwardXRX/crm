package com.edward.crm_ssh.settings.service;

import com.edward.crm_ssh.exception.UpdatePwdException;
import com.edward.crm_ssh.settings.domain.User;

import javax.security.auth.login.LoginException;
import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.settings.service
 * @ClassName: UserService
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/10 18:42
 * @Version: 1.0
 */
public interface UserService {

    User login(String loginAct, String loginPwd, String ip) throws LoginException, com.edward.crm_ssh.exception.LoginException;

    List<User> getUserList();

    Boolean updatePwd(String id,String loginAct, String oldPwd, String newPwd) throws UpdatePwdException;
}
