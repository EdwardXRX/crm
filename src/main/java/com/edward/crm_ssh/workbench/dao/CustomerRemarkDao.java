package com.edward.crm_ssh.workbench.dao;


import com.edward.crm_ssh.workbench.domain.CustomerRemark;

import java.util.List;

public interface CustomerRemarkDao {

    int save(CustomerRemark customerRemark);

    List<CustomerRemark> getRemarkListByCid(String customerId);

    int saveRemark(CustomerRemark cr);

    int updateRemark(CustomerRemark cr);

    int deleteRemark(String id);

    int getCountByCids(String[] ids);

    int deleteByCids(String[] ids);
}
