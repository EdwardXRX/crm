package com.edward.crm_ssh.workbench.service.impl;


import com.edward.crm_ssh.workbench.dao.CustomerDao;
import com.edward.crm_ssh.workbench.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: WEB_CMR
 * @Package: com.edwardxrx.crm.workbench.service.imple
 * @ClassName: CustomerServiceImpl
 * @Author: EdwardX
 * @Description:
 * @Date: 2020/12/23 21:25
 * @Version: 1.0
 */

@Service("CustomerServiceImpl")
public class CustomerServiceImpl implements CustomerService {

    @Resource
    private CustomerDao customerDao;


    @Override
    public List<String> getCustomerName(String name) {
        List<String> list =  customerDao.getCustomerName(name);
        return list;
    }
}
