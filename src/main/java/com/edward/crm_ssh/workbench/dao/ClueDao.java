package com.edward.crm_ssh.workbench.dao;

import com.edward.crm_ssh.workbench.domain.Clue;

import java.util.List;
import java.util.Map;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.workbench.dao
 * @ClassName: ClueDao
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/13 19:20
 * @Version: 1.0
 */


public interface ClueDao {

    List<Clue> getClueListByCondition(Map<String, Object> map);

    int getTotalByCondition(Map<String, Object> map);

    int save(Clue clue);
}
