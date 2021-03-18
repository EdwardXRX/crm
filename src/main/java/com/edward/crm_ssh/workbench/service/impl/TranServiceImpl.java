package com.edward.crm_ssh.workbench.service.impl;

import com.edward.crm_ssh.utils.DateTimeUtil;
import com.edward.crm_ssh.utils.UUIDUtil;
import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.dao.CustomerDao;
import com.edward.crm_ssh.workbench.dao.TranDao;
import com.edward.crm_ssh.workbench.dao.TranHistoryDao;
import com.edward.crm_ssh.workbench.dao.TranRemarkDao;
import com.edward.crm_ssh.workbench.domain.*;
import com.edward.crm_ssh.workbench.service.TranService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.workbench.service.impl
 * @ClassName: TranServiceImpl
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/15 14:09
 * @Version: 1.0
 */

@Service("TranServiceImpl")
public class TranServiceImpl implements TranService {

    @Resource
    private TranDao tranDao;

    @Resource
    private TranHistoryDao tranHistoryDao;

    @Resource
    private CustomerDao customerDao;

    @Resource
    private TranRemarkDao tranRemarkDao;

    @Override
    public boolean save(Tran t, String customerName) {
        /*
         *   添加交易之前，参数就少了customerId
         *   先处理客户相关需求
         *
         *       （1）判断customerName，根据客户名进行精准查询
         *               如果没有，则新建
         *               否则，取出customerId
         *   （2）经过上面操作，t对象就全了，执行交易添加操作
         *       （3）交易完成后，生成一个历史记录
         *
         *
         * */

        boolean flag = true;

        Customer customer = customerDao.getCustomerByName(customerName);
        if (customer == null) {
            //需要创建用户
            customer = new Customer();
            customer.setId(UUIDUtil.getUUID());
            customer.setName(customerName);
            customer.setCreateTime(DateTimeUtil.getSysTime());
            customer.setCreateBy(t.getCreateBy());
            customer.setContactSummary(t.getContactSummary());
            customer.setNextContactTime(t.getNextContactTime());
            customer.setOwner(t.getOwner());

            int count1 = customerDao.save(customer);
            if (count1 != 1)
                flag = false;
        }

        //通过以上对用户的处理，不论是查询查来的用户，还是新建的用户，都已经有了

        t.setCustomerId(customer.getId());

        int count2 = tranDao.save(t);
        if (count2 != 1)
            flag = false;


        //添加交易
        TranHistory th = new TranHistory();
        th.setId(UUIDUtil.getUUID());
        th.setTranId(t.getId());
        th.setStage(t.getStage());
        th.setMoney(t.getMoney());
        th.setExpectedDate(t.getExpectedDate());
        th.setCreateTime(DateTimeUtil.getSysTime());
        th.setCreateBy(t.getCreateBy());

        int count3 = tranHistoryDao.save(th);
        if (count3 != 1)
            flag = false;


        return flag;
    }

    @Override
    public PaginationVO<Tran> pageList(Map<String, Object> map) {
        System.out.println("进入到TranServiceImpl中的pageList方法中");

        //取得total
        int total = tranDao.getTotalByCondition(map);

        System.out.println("total:" + total);

        //取得dataList
        List<Tran> dataList = tranDao.getTranListByCondition(map);

        if (dataList == null) {
            System.out.println("dataList 为空");
        } else {
            System.out.println("dataList  的条数为" + dataList.size());
        }

        System.out.println(dataList.get(0).getContactsId());
        System.out.println(dataList.get(0).getCustomerId());

        //封装成vo
        PaginationVO<Tran> vo = new PaginationVO<Tran>();

        vo.setTotal(total);
        vo.setDataList(dataList);

        //返回
        return vo;
    }

    @Override
    public Tran detail(String id) {
        Tran t = tranDao.detail(id);
        return t;
    }

    @Override
    public List<TranHistory> getHistoryListByTranId(String tranId) {

        List<TranHistory> thList = tranHistoryDao.getHistoryListByTranId(tranId);
        return thList;
    }

    @Override
    public boolean changeStage(Tran t) {
        boolean flag = true;

        int count1 = tranDao.changeStage(t);

        if (count1 != 1)
            flag = false;

        //阶段改变之后，我们需要生成一条交易历史
        TranHistory th = new TranHistory();
        th.setStage(t.getStage());
        th.setId(UUIDUtil.getUUID());
        th.setPossibility(t.getPossibility());
        th.setCreateBy(t.getEditBy());
        th.setCreateTime(t.getEditTime());
        th.setExpectedDate(t.getExpectedDate());
        th.setMoney(t.getMoney());
        th.setTranId(t.getId());

        //添加一条交易历史
        int count2 = tranHistoryDao.save(th);

        System.out.println("count2: =====" + count2);
        if (count2 != 1)
            flag = false;
        return flag;
    }

    @Override
    public boolean saveRemark(TranRemark tr) {
        boolean flag = true;

        int count = tranRemarkDao.saveRemark(tr);
        if (count != 1)
            flag = false;

        return flag;
    }

    @Override
    public boolean deleteRemark(String id) {

        int count = tranRemarkDao.deleteRemark(id);

        boolean flag = true;

        if (count == 1) {
            flag = true;
        } else
            flag = false;

        return flag;
    }

    @Override
    public boolean updateRemark(TranRemark tr) {
        int count = tranRemarkDao.updateRemark(tr);
        boolean flag = true;
        if (count != 1)
            flag = false;
        return flag;
    }

    @Override
    public List<TranRemark> getRemarkListByAid(String tranId) {

        List<TranRemark> trList = tranRemarkDao.getRemarkListByAid(tranId);
        return trList;
    }

    @Override
    public boolean delete(String[] ids) {

        System.out.println("进入tranServiceImpl中的delete方法");

        boolean flag = true;
        //首先查询我们需要删除的备注数量
        int count = tranRemarkDao.getCountByIds(ids);

        System.out.println("111111111");

        //然后再查询我们删除的备注条数
        int count2 = tranRemarkDao.deleteByIds(ids);
        //两者进行比较，如果成功之后再删除我们的真正要删除的活动记录

        System.out.println("222222222");

        if (count != count2) {
            System.out.println("不成功");
            flag = false;
        }

        System.out.println("count" + count);
        System.out.println("count2" + count2);

        //查询我们要删除的交易历史条数
        int count5 = tranHistoryDao.getCountByIds(ids);

        System.out.println("count5" + count5);
        //查询我们删除的交易条数
        int count6 = tranHistoryDao.deleteByIds(ids);

        System.out.println("count6" + count6);

        if (count5 != count6) {
            System.out.println("不成功");
            flag = false;
        }


        int count3 = tranDao.delete(ids);

        if (count3 != ids.length) {
            System.out.println("删除交易失败");
            flag = false;
        }
        //返回一个标志位
        return flag;
    }

    @Override
    public Map<String, Object> getCharts() {

        Map<String, Object> map = new HashMap<>();

        //取得total
        int total = tranDao.getTotal();

        System.out.println("total:" + total);

        //取得dataList
        List<Map<String, Object>> dataList = tranDao.getCharts();

        if (dataList == null)
            System.out.println("空2");

        //将两个数据保存至map中
        map.put("total", total);
        map.put("dataList", dataList);

        return map;
    }

    @Override
    public List<Tran> getTranList() {

        List<Tran> tranList = tranDao.getTranList();

        return tranList;
    }

    @Override
    public String[] getTranIdsByCustomerId(String customerId) {
        System.out.println("进入到通过客户id来删除交易的方法中");

        String[] tranIds = tranDao.getTranIdsByCustomerId(customerId);

        //返回一个标志位
        return tranIds;
    }


}
