package com.edward.crm_ssh.workbench.service;

import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.domain.Tran;

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
}
