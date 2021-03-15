package com.edward.crm_ssh.workbench.service.impl;

import com.edward.crm_ssh.settings.dao.UserDao;
import com.edward.crm_ssh.settings.domain.User;
import com.edward.crm_ssh.utils.DateTimeUtil;
import com.edward.crm_ssh.utils.UUIDUtil;
import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.dao.*;
import com.edward.crm_ssh.workbench.domain.*;
import com.edward.crm_ssh.workbench.service.ClueService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.workbench.service.impl
 * @ClassName: ClueServiceImpl
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/13 19:19
 * @Version: 1.0
 */

@Service("ClueServiceImpl")
public class ClueServiceImpl implements ClueService {

    @Resource
    private ClueDao clueDao;

    @Resource
    private UserDao userDao;

    @Resource
    private CustomerDao customerDao;

    @Resource
    private ContactsDao contactsDao;

    @Resource
    private ClueRemarkDao clueRemarkDao;

    @Resource
    private CustomerRemarkDao customerRemarkDao;

    @Resource
    private ContactsRemarkDao contactsRemarkDao;

    @Resource
    private ClueActivityRelationDao clueActivityRelationDao;

    @Resource
    private ContactsActivityRelationDao contactsActivityRelationDao;

    @Resource
    private TranDao tranDao;

    @Resource
    private TranHistoryDao tranHistoryDao;

    @Override
    public boolean bundActivity(List<Map<String, String>> list) {
        int count = clueDao.bundActivity(list);

        boolean flag = true;
        if (count != list.size())
            flag = false;

        return flag;
    }

    @Override
    public PaginationVO<Clue> pageList(Map<String, Object> map) {

        System.out.println("进入到ClueServiceImpl文件中得pageList中");

        //取得total
        int total = clueDao.getTotalByCondition(map);

        System.out.println("total:" + total);

        //取得dataList
        List<Clue> dataList = clueDao.getClueListByCondition(map);

        //封装成vo
        PaginationVO<Clue> vo = new PaginationVO<Clue>();

        vo.setTotal(total);
        vo.setDataList(dataList);

        //返回
        return vo;
    }

    @Override
    public boolean save(Clue clue) {
        int count = clueDao.save(clue);
        boolean flag = true;

        if (count != 1)
            flag = false;

        return flag;

    }

    @Override
    public Map<String, Object> getUserListAndClue(String id) {

        //取uList
        List<User> uList = userDao.getUserList();

        //取得单条活动通过id值
        Clue c = clueDao.getById(id);

        //将uList打包进map
        Map<String, Object> map = new HashMap<>();

        map.put("uList", uList);
        map.put("c", c);

        //返回map

        return map;
    }

    @Override
    public Boolean update(Clue clue) {
        Boolean flag = true;
        int count = clueDao.update(clue);
        System.out.println("count===" + count);

        if (count != 1)
            flag = false;

        return flag;
    }

    @Override
    public boolean deleteIds(String[] ids) {

        int count = clueDao.deleteIds(ids);

        boolean flag = true;
        if (count != ids.length) {
            System.out.println("删除线索失败");
            flag = false;
        }
        //返回一个标志位
        return flag;
    }

    @Override
    public Clue detail(String id) {
        Clue clue = clueDao.detail(id);

        return clue;
    }

    @Override
    public boolean unbund(String id) {

        int count = clueDao.unbund(id);

        boolean flag = true;
        if (count != 1)
            flag = false;
        return flag;
    }

    @Override
    public boolean convert(String clueId, Tran t, String createBy) {

        String createTime = DateTimeUtil.getSysTime();
        boolean flag = true;

        //(1)获取到线索 id， 通过线索 id 获取线索对象（线索对象当中封装了线索的信息）
        Clue c = clueDao.getById(clueId);

        //(2) 通过线索对象提取客户信息， 当该客户不存在的时候， 新建客户（根据公司的名称精确匹配， 判断该客户是否存在！）
        String company = c.getCompany();
        Customer cus = customerDao.getCustomerByName(company);
        //如果cus为空，则说明之前没有这个客户，需要新建一个
        if (cus == null) {
            cus = new Customer();
            cus.setId(UUIDUtil.getUUID());
            cus.setAddress(c.getAddress());
            cus.setWebsite(c.getWebsite());
            cus.setOwner(c.getOwner());
            cus.setName(c.getCompany());
            cus.setPhone(c.getPhone());
            cus.setNextContactTime(c.getNextContactTime());
            cus.setDescription(c.getDescription());
            cus.setCreateBy(createBy);
            cus.setCreateTime(createTime);
            cus.setContactSummary(c.getContactSummary());
            //添加客户
            int count1 = customerDao.save(cus);
            if (count1 != 1) {
                flag = false;
            }
        }

        //经过第二步处理，客户的信息已经拥有了
        //----------------------------------------------------
        //(3) 通过线索对象提取联系人信息， 保存联系人
        Contacts con = new Contacts();
        con.setId(UUIDUtil.getUUID());
        con.setSource(c.getSource());
        con.setOwner(c.getOwner());
        con.setCreateBy(createBy);
        con.setNextContactTime(c.getNextContactTime());
        con.setJob(c.getJob());
        con.setMphone(c.getMphone());
        con.setEmail(c.getEmail());
        con.setDescription(c.getDescription());
        con.setFullname(c.getFullname());
        con.setCustomerId(cus.getId());
        con.setCreateTime(createTime);
        con.setContactSummary(c.getContactSummary());
        con.setAddress(c.getAddress());
        con.setAppellation(c.getAppellation());
        int count2 = contactsDao.save(con);
        if (count2 != 1)
            flag = false;


        //经过第三步，联系人已经获得
        //如果需要联系人的id，直接使用con.getId()
        //(4) 线索备注转换到客户备注以及联系人备注
        //查询出与该线索关联的备注信息列表
        List<ClueRemark> clueRemarkList = clueRemarkDao.getListByClueId(clueId);

        for (ClueRemark clueRemark : clueRemarkList) {
            //取出备注信息（主要转换到客户备注和联系人备注的就是这个备注信息）
            String noteContent = clueRemark.getNoteContent();

            //创建客户对象备注，添加客户备注
            CustomerRemark customerRemark = new CustomerRemark();
            customerRemark.setId(UUIDUtil.getUUID());
            customerRemark.setCreateBy(createBy);
            customerRemark.setCreateTime(createTime);
            customerRemark.setCustomerId(cus.getId());
            customerRemark.setEditFlag("0");
            customerRemark.setNoteContent(noteContent);
            int count3 = customerRemarkDao.save(customerRemark);
            if (count3 != 1)
                flag = false;

            //创建联系人对象，添加联系人
            ContactsRemark contactsRemark = new ContactsRemark();
            contactsRemark.setId(UUIDUtil.getUUID());
            contactsRemark.setCreateBy(createBy);
            contactsRemark.setCreateTime(createTime);
            contactsRemark.setContactsId(cus.getId());
            contactsRemark.setEditFlag("0");
            contactsRemark.setNoteContent(noteContent);
            int count4 = contactsRemarkDao.save(contactsRemark);
            if (count4 != 1)
                flag = false;
        }


        //(5) “线索和市场活动” 的关系转换到“联系人和市场活动” 的关系
        //查询出与该条线索关联的市场活动
        List<ClueActivityRelation> ClueActivityRelationList = clueActivityRelationDao.getListByClueId(clueId);
        for (ClueActivityRelation clueActivityRelation : ClueActivityRelationList) {
            //从每一条遍历出来的记录中取出关联的市场id
            String activityId = clueActivityRelation.getActivityId();
            //创建 联系人与市场活动的关联对象，让第三步生成的联系人与市场活动做关联
            ContactsActivityRelation contactsActivityRelation = new ContactsActivityRelation();
            contactsActivityRelation.setId(UUIDUtil.getUUID());
            contactsActivityRelation.setActivityId(activityId);
            contactsActivityRelation.setContactsId(con.getId());
            //添加联系人是市场活动的关系
            int count5 = contactsActivityRelationDao.save(contactsActivityRelation);
            if (count5 != 1)
                flag = false;
        }

        //(6) 如果有创建交易需求， 创建一条交易
        if (t != null) {
            //创建交易
            //id,name,money,expectedDate,stage,activityId,createBy,createBy

            //接下来们可以通过第一部生成的c对象，取出点信息，继续完善对t对象的封装

            t.setSource(c.getSource());
            t.setOwner(c.getOwner());
            t.setNextContactTime(c.getNextContactTime());
            t.setDescription(c.getDescription());
            t.setCustomerId(cus.getId());
            t.setContactSummary(cus.getContactSummary());
            t.setContactsId(con.getId());

            //添加交易
            int count6 = tranDao.save(t);
            if (count6 != 1)
                flag = false;

            //(7) 如果创建了交易， 则创建一条该交易下的交易历史
            //一对多的关系

            TranHistory tranHistory = new TranHistory();
            tranHistory.setId(UUIDUtil.getUUID());
            tranHistory.setCreateBy(createBy);
            tranHistory.setCreateTime(createTime);
            tranHistory.setExpectedDate(t.getExpectedDate());
            tranHistory.setMoney(t.getMoney());
            tranHistory.setStage(t.getStage());
            tranHistory.setTranId(t.getId());

            //添加交易历史
            int count7 = tranHistoryDao.save(tranHistory);
            if (count7 != 1)
                flag = false;


        }

        //(8) 删除线索备注
        for (ClueRemark clueRemark : clueRemarkList) {
            int count8 = clueRemarkDao.delete(clueRemark);

            if (count8 != 1)
                flag = false;
        }

        //(9) 删除线索和市场活动的关系
        for (ClueActivityRelation clueActivityRelation : ClueActivityRelationList) {
            int count9 = clueActivityRelationDao.delete(clueActivityRelation);
            if (count9 != 1)
                flag = false;

        }

        //(10) 删除线索
        int count10 = clueDao.delete(clueId);
        if (count10 != 1)
            flag = false;


        return flag;


    }
}
