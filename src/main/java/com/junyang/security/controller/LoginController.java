package com.junyang.security.controller;

import com.junyang.common.Constants;
import com.junyang.common.model.ApiResponse;
import com.junyang.common.model.tree.Node;
import com.junyang.common.utils.ControllerUtil;
import com.junyang.security.model.Menu;
import com.junyang.security.service.LoginService;
import com.junyang.security.service.MenuService;
import com.junyang.security.vo.LoginMsg;
import com.junyang.security.vo.SecurityDataVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * 登陆处理
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value="security/")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private MenuService menuService;

	@Autowired
	private LoginService loginService;
	
	/**
	 * 登陆
	 */
	@RequestMapping(value="login")
	@ResponseBody
	private ApiResponse login(@RequestBody SecurityDataVo securityDataVo, HttpServletRequest request){
		return loginService.loginValidate(securityDataVo);
	}
	/**
	 * 退出
	 */
	@RequestMapping(value="logout")
	private ModelAndView logout(){
		Subject subject = SecurityUtils.getSubject();
		Session session = subject.getSession();
		session.removeAttribute(Constants.SESSION_USER);
		//shiro销毁登录
		subject.logout();
	   return new ModelAndView("redirect:/login.jsp");
	}

	public  void  aaad() {
		Map<String,Object> map = new HashMap<String,Object>();
		LoginMsg loginMsg = new LoginMsg();
		if(loginMsg.isIslogin()){//身份验证成功
			//获取所有的菜单
			List<Menu> listMenu = new ArrayList<Menu>(menuService.findPersonMenusByPersonId(ControllerUtil.getCurrentUser().getId()));

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
		}else{//身份验证失败
			map.put("loginMsg",loginMsg);
		}
	}

}
