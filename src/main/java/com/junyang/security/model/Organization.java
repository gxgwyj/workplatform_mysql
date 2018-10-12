package com.junyang.security.model;

public class Organization {
    private String oId;

    private String oCode;

    private String oName;

    private String oPid;

    private String state;

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId == null ? null : oId.trim();
    }

    public String getoCode() {
        return oCode;
    }

    public void setoCode(String oCode) {
        this.oCode = oCode == null ? null : oCode.trim();
    }

    public String getoName() {
        return oName;
    }

    public void setoName(String oName) {
        this.oName = oName == null ? null : oName.trim();
    }

    public String getoPid() {
        return oPid;
    }

    public void setoPid(String oPid) {
        this.oPid = oPid == null ? null : oPid.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}