package com.edward.crm_ssh.workbench.dao;


import com.edward.crm_ssh.workbench.domain.Tran;
import com.edward.crm_ssh.workbench.domain.TranRemark;

import java.util.List;
import java.util.Map;

public interface TranDao {

    int save(Tran t);

    Tran detail(String id);

    int changeStage(Tran t);

    int getTotal();

    List<Map<String, Object>> getCharts();

    int getTotalByCondition(Map<String, Object> map);

    List<Tran> getTranListByCondition(Map<String, Object> map);

    int delete(String[] ids);
}
