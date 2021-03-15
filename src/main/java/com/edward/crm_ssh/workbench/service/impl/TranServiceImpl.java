package com.edward.crm_ssh.workbench.service.impl;

import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.dao.TranDao;
import com.edward.crm_ssh.workbench.domain.Clue;
import com.edward.crm_ssh.workbench.domain.Tran;
import com.edward.crm_ssh.workbench.service.TranService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
