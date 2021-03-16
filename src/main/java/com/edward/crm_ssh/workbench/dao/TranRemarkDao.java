package com.edward.crm_ssh.workbench.dao;

import com.edward.crm_ssh.workbench.domain.TranRemark;

import java.util.List;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.workbench.dao
 * @ClassName: TranRemarkDao
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/16 15:05
 * @Version: 1.0
 */
public interface TranRemarkDao {

    int saveRemark(TranRemark tr);

    int deleteRemark(String id);

    int updateRemark(TranRemark tr);

    List<TranRemark> getRemarkListByAid(String tranId);

    int getCountByIds(String[] ids);

    int deleteByIds(String[] ids);
}
