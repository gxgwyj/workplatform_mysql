package com.junyang.security.vo;
/**
 * 资源菜单
 * @author Administrator
 *
 */
public class MenuVo {
	private String id;
	private String name;
	private String title;
	private String icon;
	private String url;
	private String pid;
	private String pName;
	private String state;
	private String mcode;
	private String isLeaf;
	
	public MenuVo() {
		super();
	}
	public MenuVo(String id, String name, String title, String icon, String url,
			String pid, String state,String isLeaf,String mcode) {
		super();
		this.id = id;
		this.name = name;
		this.title = title;
		this.icon = icon;
		this.url = url;
		this.pid = pid;
		this.state = state;
		this.isLeaf = isLeaf;
		this.mcode = mcode;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getState() {
		return state;
	}
	public String getMcode() {
		return mcode;
	}
	public void setMcode(String mcode) {
		this.mcode = mcode;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public void setState(String state) {
		this.state = state;
	}
	

}
