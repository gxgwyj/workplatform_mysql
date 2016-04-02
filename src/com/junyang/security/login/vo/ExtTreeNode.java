package com.junyang.security.login.vo;

import java.util.ArrayList;

public class ExtTreeNode {
	//id
	private String id;
	//标题
	private String text;
	//是否子节点
	private boolean leaf;
	//链接
	private String url;
	//子节点
	private ArrayList<ExtTreeNode> children;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isLeaf() {
		return leaf;
	}
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public ArrayList<ExtTreeNode> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<ExtTreeNode> children) {
		this.children = children;
	}
	

}
