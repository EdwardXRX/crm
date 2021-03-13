package com.edward.crm_ssh.workbench.service.impl;

import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.dao.ClueDao;
import com.edward.crm_ssh.workbench.domain.Clue;
import com.edward.crm_ssh.workbench.service.ClueService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
}
