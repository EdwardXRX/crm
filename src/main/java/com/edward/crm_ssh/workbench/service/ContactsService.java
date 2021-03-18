package com.edward.crm_ssh.workbench.service;

import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.domain.Activity;
import com.edward.crm_ssh.workbench.domain.Contacts;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.workbench.service
 * @ClassName: ContactsService
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/15 22:41
 * @Version: 1.0
 */
public interface ContactsService {

    List<Contacts> getContactsList();

    PaginationVO<Contacts> pageList(Map<String, Object> map);
}
