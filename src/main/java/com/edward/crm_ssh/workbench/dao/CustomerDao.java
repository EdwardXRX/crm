package com.edward.crm_ssh.workbench.dao;



import com.edward.crm_ssh.workbench.domain.Customer;

import java.util.List;

public interface CustomerDao {

    Customer getCustomerByName(String name);

    int save(Customer cus);

    List<String> getCustomerName(String name);
}
