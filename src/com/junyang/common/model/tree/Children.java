package com.junyang.common.model.tree;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/**
 * 孩子列表列
 * @author Administrator
 *
 */
public class Children {
	//数据列表
	private List list = new ArrayList();
	//孩子个数
	public int getSize(){
		return list.size();
	}
	//添加孩子
    public void addChild(Node node){
    	list.add(node);
    }
    //拼接孩子节点的JSON字符串  
    public String toString(){
    	StringBuffer sbf = new StringBuffer("[");
    	//便利拼接子节点
    	for(Iterator it = list.iterator();it.hasNext();){
    		//回调node的toString方法拼接字符串（类似递归）
    		sbf.append(((Node)it.next()).toString()).append(",");
    	}
    	String result =sbf.toString().substring(0,sbf.toString().length()-1);
    	result = result+"]";
    	return result;
    }
}
