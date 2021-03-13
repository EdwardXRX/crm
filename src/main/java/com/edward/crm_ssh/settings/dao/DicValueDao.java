package com.edward.crm_ssh.settings.dao;



import com.edward.crm_ssh.settings.domain.DicValue;

import java.util.List;

/**
 * @ProjectName: WEB_CMR
 * @Package: com.edward.crm_s.settings.dao
 * @ClassName: DicValueDao
 * @Author: EdwardX
 * @Description:
 * @Date: 2020/12/17 15:09
 * @Version: 1.0
 */
public interface DicValueDao {
    List<DicValue> getValueList(String code);
}
