package com.junyang.security.model;

import java.util.List;

/**
 * 资源菜单
 * @author Administrator
 *
 */
public class Menu {
	private String id;
	private String name;
	private String title;
	private String icon;
	private String url;
	private String pid;
	private String state;
	private String isLeaf;
	private String mcode;

	private List<Menu> subMenus;
	
	public Menu() {
		super();
	}
	public Menu(String id, String name, String title, String icon, String url,
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
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
	}
	public String getMcode() {
		return mcode;
	}
	public void setMcode(String mcode) {
		this.mcode = mcode;
	}

	public List<Menu> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<Menu> subMenus) {
		this.subMenus = subMenus;
	}
}
