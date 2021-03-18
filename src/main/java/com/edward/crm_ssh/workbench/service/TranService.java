package com.edward.crm_ssh.workbench.service;

import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.domain.Tran;
import com.edward.crm_ssh.workbench.domain.TranHistory;
import com.edward.crm_ssh.workbench.domain.TranRemark;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.workbench.service
 * @ClassName: TranService
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/15 14:07
 * @Version: 1.0
 */
public interface TranService {

    PaginationVO<Tran> pageList(Map<String, Object> map);

    boolean save(Tran t, String customerName);

    Tran detail(String id);

    List<TranHistory> getHistoryListByTranId(String tranId);

    boolean changeStage(Tran t);

    boolean saveRemark(TranRemark tr);

    boolean deleteRemark(String id);

    boolean updateRemark(TranRemark tr);

    List<TranRemark> getRemarkListByAid(String tranId);

    boolean delete(String[] ids);

    Map<String, Object> getCharts();

    List<Tran> getTranList();

    String[] getTranIdsByCustomerId(String customerId);
}
