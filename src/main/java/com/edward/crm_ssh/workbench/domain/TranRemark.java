package com.edward.crm_ssh.workbench.domain;

/**
 * @ProjectName: crm
 * @Package: com.edward.crm_ssh.workbench.domain
 * @ClassName: TranRemark
 * @Author: EdwardX
 * @Description:
 * @Date: 2021/3/16 14:58
 * @Version: 1.0
 */
public class TranRemark {
    private String id;
    private String noteContent;       //备注信息
    private String createTime;
    private String createBy;
    private String editTime;
    private String editBy;
    private String editFlag;      //是否修改过的标记
    private String tranId;    //外键

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getEditTime() {
        return editTime;
    }

    public void setEditTime(String editTime) {
        this.editTime = editTime;
    }

    public String getEditBy() {
        return editBy;
    }

    public void setEditBy(String editBy) {
        this.editBy = editBy;
    }

    public String getEditFlag() {
        return editFlag;
    }

    public void setEditFlag(String editFlag) {
        this.editFlag = editFlag;
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }
}
