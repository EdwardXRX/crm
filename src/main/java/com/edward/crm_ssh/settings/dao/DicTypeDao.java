package com.edward.crm_ssh.settings.dao;


import com.edward.crm_ssh.settings.domain.DicType;

import java.util.List;

/**
 * @ProjectName: WEB_CMR
 * @Package: com.edwardxrx.crm.settings.dao
 * @ClassName: DicTypeDao
 * @Author: EdwardX
 * @Description:
 * @Date: 2020/12/17 15:09
 * @Version: 1.0
 */
public interface DicTypeDao {
    List<DicType> getTypeList();
}
