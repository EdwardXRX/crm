package com.edward.crm_ssh.workbench.service.impl;

import com.edward.crm_ssh.workbench.dao.ContactsDao;
import com.edward.crm_ssh.workbench.domain.Contacts;
import com.edward.crm_ssh.workbench.service.ContactsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.workbench.service.impl
 * @ClassName: ContactsServiceImpl
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/15 22:42
 * @Version: 1.0
 */

@Service("ContactsServiceImpl")
public class ContactsServiceImpl implements ContactsService {

    @Resource
    private ContactsDao contactsDao;


    @Override
    public List<Contacts> getContactsList() {

        List<Contacts> contactsList = contactsDao.getContactsList();

        return contactsList;
    }
}
