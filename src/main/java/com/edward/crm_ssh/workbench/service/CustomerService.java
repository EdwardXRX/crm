package com.edward.crm_ssh.workbench.service;

import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.domain.Activity;
import com.edward.crm_ssh.workbench.domain.Contacts;
import com.edward.crm_ssh.workbench.domain.Customer;
import com.edward.crm_ssh.workbench.domain.CustomerRemark;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: WEB_CMR
 * @Package: com.edwardxrx.crm.workbench.service
 * @ClassName: CustomerService
 * @Author: EdwardX
 * @Description:
 * @Date: 2020/12/23 21:24
 * @Version: 1.0
 */
public interface CustomerService {
    List<String> getCustomerName(String name);

    PaginationVO<Customer> pageList(Map<String, Object> map);

    boolean save(Customer customer);

    Customer detail(String id);

    List<CustomerRemark> getRemarkListByCid(String customerId);

    boolean saveRemark(CustomerRemark cr);

    boolean updateRemark(CustomerRemark cr);

    boolean deleteRemark(String id);

    boolean delete(String[] ids);
}
