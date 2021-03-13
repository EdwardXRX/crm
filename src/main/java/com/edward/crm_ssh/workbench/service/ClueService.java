package com.edward.crm_ssh.workbench.service;

import com.edward.crm_ssh.vo.PaginationVO;
import com.edward.crm_ssh.workbench.domain.Clue;

import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.workbench.service
 * @ClassName: ClueService
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/13 19:18
 * @Version: 1.0
 */
public interface  ClueService {

    PaginationVO<Clue> pageList(Map<String, Object> map);

    boolean save(Clue clue);


}
