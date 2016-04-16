package com.junyang.security.model;

public class Privilege {
    private String pId;

    private String pMaster;

    private String pMastervalue;

    private String pMid;

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId == null ? null : pId.trim();
    }

    public String getpMaster() {
        return pMaster;
    }

    public void setpMaster(String pMaster) {
        this.pMaster = pMaster == null ? null : pMaster.trim();
    }

    public String getpMastervalue() {
        return pMastervalue;
    }

    public void setpMastervalue(String pMastervalue) {
        this.pMastervalue = pMastervalue == null ? null : pMastervalue.trim();
    }

    public String getpMid() {
        return pMid;
    }

    public void setpMid(String pMid) {
        this.pMid = pMid == null ? null : pMid.trim();
    }
}