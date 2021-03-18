package com.edward.crm_ssh.workbench.service.impl;


import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.dao.CustomerDao;
import com.edward.crm_ssh.workbench.dao.CustomerRemarkDao;
import com.edward.crm_ssh.workbench.domain.*;
import com.edward.crm_ssh.workbench.service.CustomerService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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

    @Resource
    private CustomerRemarkDao customerRemarkDao;

    @Override
    public List<String> getCustomerName(String name) {
        List<String> list = customerDao.getCustomerName(name);
        return list;
    }

    @Override
    public PaginationVO<Customer> pageList(Map<String, Object> map) {
        System.out.println("进入到CustomerServiceImpl文件中得pageList中");
        //取得total
        int total = customerDao.getTotalByCondition(map);

        System.out.println("total:" + total);

        //取得dataList
        List<Customer> dataList = customerDao.getCustomerListByCondition(map);

        if (dataList == null) {
            System.out.println("dataList为空");
        } else System.out.println("dataList不为空");
        //封装成vo

        PaginationVO<Customer> vo = new PaginationVO<Customer>();

        vo.setTotal(total);
        vo.setDataList(dataList);

        //返回

        return vo;
    }

    @Override
    public boolean save(Customer customer) {
        int count = customerDao.save(customer);

        boolean flag = true;
        if (count != 1) {
            flag = false;
        }
        return flag;
    }

    @Override
    public Customer detail(String id) {

        Customer c = customerDao.detail(id);

        return c;
    }

    @Override
    public List<CustomerRemark> getRemarkListByCid(String customerId) {

        List<CustomerRemark> crList = customerRemarkDao.getRemarkListByCid(customerId);
        return crList;
    }

    @Override
    public boolean saveRemark(CustomerRemark cr) {
        boolean flag = true;

        int count = customerRemarkDao.saveRemark(cr);
        if (count != 1)
            flag = false;

        return flag;
    }

    @Override
    public boolean updateRemark(CustomerRemark cr) {
        int count = customerRemarkDao.updateRemark(cr);
        boolean flag = true;
        if (count != 1)
            flag = false;
        return flag;
    }

    @Override
    public boolean deleteRemark(String id) {

        int count = customerRemarkDao.deleteRemark(id);

        boolean flag = true;

        if (count == 1) {
            flag = true;
        } else
            flag = false;

        return flag;
    }

    @Override
    public boolean delete(String[] ids) {

        boolean flag = true;
        //首先查询我们需要删除的备注数量
        int count = customerRemarkDao.getCountByCids(ids);

        //然后再查询我们删除的备注条数
        int count2 = customerRemarkDao.deleteByCids(ids);
        //两者进行比较，如果成功之后再删除我们的真正要删除的活动记录

        if (count != count2) {
            System.out.println("不成功");
            flag = false;
        }

        int count3 = customerDao.delete(ids);

        if (count3 != ids.length) {
            System.out.println("删除客户失败");
            flag = false;
        }
        //返回一个标志位
        return flag;
    }
}
