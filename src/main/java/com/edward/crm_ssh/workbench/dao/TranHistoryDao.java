package com.edward.crm_ssh.workbench.dao;

import com.edward.crm_ssh.workbench.domain.TranHistory;

import java.util.List;

public interface TranHistoryDao {

    int save(TranHistory tranHistory);

    List<TranHistory> getHistoryListByTranId(String tranId);

    int getCountByIds(String[] ids);

    int deleteByIds(String[] ids);
}
