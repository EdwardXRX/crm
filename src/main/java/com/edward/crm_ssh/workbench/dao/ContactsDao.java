package com.edward.crm_ssh.workbench.dao;


import com.edward.crm_ssh.workbench.domain.Contacts;

import java.util.List;

public interface ContactsDao {

    int save(Contacts con);

    List<Contacts> getContactsList();

}
