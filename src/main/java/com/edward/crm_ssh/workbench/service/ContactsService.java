package com.edward.crm_ssh.workbench.service;

import com.edward.crm_ssh.workbench.domain.Contacts;

import java.util.List;

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
}
