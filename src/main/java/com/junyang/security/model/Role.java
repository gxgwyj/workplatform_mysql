package com.junyang.security.model;

public class Role {
    private String rId;

    private String rName;
    
    private String rCode;

    private String state;

    public String getrId() {
        return rId;
    }

    public void setrId(String rId) {
        this.rId = rId == null ? null : rId.trim();
    }

    public String getrName() {
        return rName;
    }

    public void setrName(String rName) {
        this.rName = rName == null ? null : rName.trim();
    }

    public String getState() {
        return state;
    }

    public String getrCode() {
		return rCode;
	}

	public void setrCode(String rCode) {
		this.rCode = rCode;
	}

	public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }
}