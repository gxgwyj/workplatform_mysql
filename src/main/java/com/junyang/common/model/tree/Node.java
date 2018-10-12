package com.junyang.common.model.tree;
/**
 * 节点类
 * @author Administrator
 *
 */
public class Node {
	 //节点编号
     private String id;
     //节点内容
     private String text;
     //父节点编号
     private String parentId;
     //菜单链接
     private String url;
     public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	//孩子节点列表
     private Children children = new Children();  
     // 先序遍历，拼接JSON字符串 
     public String toString(){
    	 StringBuffer sbf = new StringBuffer("{");
    	 sbf.append("id:").append("\"").append(id).append("\"").append(",");
    	 sbf.append("text:").append("\"").append(text).append("\"").append(",");
    	 if (children != null && children.getSize() != 0) {  
    		 sbf.append(" children : ").append(children.toString());  
    	 }else{  
    		 sbf.append("leaf : true,");  
    		 sbf.append("url :").append("\"").append(url).append("\"");  
    	  }  
    	  return sbf.append("}").toString() ;  
      }  
	public Children getChildren() {
		return children;
	}
	public void setChildren(Children children) {
		this.children = children;
	}
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
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
     
}
