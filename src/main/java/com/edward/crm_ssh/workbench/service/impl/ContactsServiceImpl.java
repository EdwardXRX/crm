package com.edward.crm_ssh.workbench.service.impl;

import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.dao.ContactsDao;
import com.edward.crm_ssh.workbench.domain.Activity;
import com.edward.crm_ssh.workbench.domain.Contacts;
import com.edward.crm_ssh.workbench.service.ContactsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Override
    public PaginationVO<Contacts> pageList(Map<String, Object> map) {
        System.out.println("进入到ContactsServiceImpl文件中得pageList中");
        //取得total
        int total = contactsDao.getTotalByCondition(map);

        System.out.println("total:" + total);

        //取得dataList
        List<Contacts> dataList = contactsDao.getContactsListByCondition(map);

        if (dataList == null) {
            System.out.println("dataList为空");
        } else System.out.println("dataList不为空");
        //封装成vo

        PaginationVO<Contacts> vo = new PaginationVO<Contacts>();

        vo.setTotal(total);
        vo.setDataList(dataList);

        //返回
        return vo;
    }
}
