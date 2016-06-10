package com.junyang.security.login.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.junyang.common.Constants;
import com.junyang.common.model.tree.Node;
import com.junyang.common.util.ControllerUtil;
import com.junyang.security.dao.PersonMapper;
import com.junyang.security.login.model.LoginMsg;
import com.junyang.security.login.service.LoginService;
import com.junyang.security.model.Menu;
import com.junyang.security.service.MenuService;

/**
 * 登陆处理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="security/")
public class LoginController { 
	private static final String VIEW_PATH_MAIN = "security/main/main";
	private static final String VIEW_PATH_LOGIN = "forward:/login.jsp";
	@Autowired
	private LoginService loginService;
	@Autowired
	private MenuService menuService;
	@Autowired 
	private PersonMapper personMapper;
	/**
	 * 登陆 
	 */
	@RequestMapping(value="login")
	private ModelAndView Login(@RequestParam("code") String code,@RequestParam("pwd") String pwd){
		Map<String,Object> map = new HashMap<String,Object>();
		LoginMsg loginMsg = new LoginMsg();
		if(code!=null && pwd!=null){
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(code, pwd); //shiro加入身份验证
			try {
				subject.login(token);
				loginMsg.setShowMsg("用户身身份验证成功！");
				loginMsg.setIslogin(true);	
			} catch (AuthenticationException e) {
				e.printStackTrace();
				loginMsg.setShowMsg("用户身份验证失败！");
				loginMsg.setIslogin(false);	
			} 
		}else{
			loginMsg.setShowMsg("用户名或密码错误！");
			loginMsg.setIslogin(false);	
		}
		if(loginMsg.isIslogin()){//身份验证成功
			//获取所有的菜单
			List<Menu> listMenu = null;
			if("admin".equals(code)  && "admin".equals(pwd)){
				//获取所有的菜单
				listMenu = menuService.findMenuList();
			}else{
				listMenu = new ArrayList<Menu>(menuService.findPersonMenusByPersonId(ControllerUtil.getCurrentUser().getId()));
			}
			
			//节点列表（散列表）
			HashMap<String, Object> nodeMap = new HashMap<String, Object>();
			//定义根节点
			Node root = null;
			//根据结果集构造节点列表（存入散列表）
			for(int i=0;i<listMenu.size();i++){
				Node node = new Node();
				node.setId(listMenu.get(i).getId());
				node.setParentId(listMenu.get(i).getPid());
				node.setText(listMenu.get(i).getName());
				node.setUrl(listMenu.get(i).getUrl());
				nodeMap.put(node.getId(), node);
			}
			//构造无序的多叉树
			Set entrySet = nodeMap.entrySet();
			Iterator iter = entrySet.iterator();
			while(iter.hasNext()){
				Node node = (Node)((Map.Entry)iter.next()).getValue();
				if(node.getParentId()==null||"-1".equals(node.getParentId())){
					//根节点
					root = node;
				}else{
					//添加子节点
					((Node)nodeMap.get(node.getParentId())).getChildren().addChild(node);
				}
			}
			//输出json字符串
			String menuTree = root.toString();
			map.put("menuTree",menuTree);
			return new ModelAndView(VIEW_PATH_MAIN,map);	
		}else{//身份验证失败
			map.put("loginMsg",loginMsg);
			return new ModelAndView(VIEW_PATH_LOGIN,map);
		}
			
			
	}
	/**
	 * 退出
	 */
	@RequestMapping(value="logout")
	private ModelAndView logout(){
		//shiro管理的session
		Subject subject = SecurityUtils.getSubject();  
		Session session = subject.getSession();
		session.removeAttribute(Constants.SESSION_USER);
		//shiro销毁登录
		subject.logout();
	   return new ModelAndView("redirect:/login.jsp");
	}

}
