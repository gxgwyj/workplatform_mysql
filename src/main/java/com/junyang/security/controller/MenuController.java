package com.junyang.security.controller;

import com.junyang.common.model.tree.TreeNode;
import com.junyang.common.utils.JsonUtil;
import com.junyang.common.utils.StringUtil;
import com.junyang.security.model.Menu;
import com.junyang.security.service.MenuService;
import com.junyang.security.vo.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Controller
@RequestMapping(value="security/menu/")
public class MenuController {
	private static final String MENUEDIT = "security/menu/menuEdit";
	private static final String MENUELIST = "security/menu/menuList";
	private static final String ROOTMENU = "rootMenu";
	private static final String REDIRECT_MENU_LIST_ACTION="redirect:menuList.do";
	@Autowired  
	private  HttpServletRequest request; 
	@Autowired
	private MenuService  menuService;
	
	@RequestMapping(value="menuEdit")
	public ModelAndView menuEdit(){
		String pId = request.getParameter("pId");
		String pName = request.getParameter("pName");
		MenuVo menuVo = new MenuVo();
		try {
			pName = java.net.URLDecoder.decode(pName,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(pId ==null  || (pId !=null &&"".equals(pId.trim()))){
			menuVo.setPid(ROOTMENU);
			menuVo.setpName(pName);
		}else{
			menuVo.setPid(pId);
			menuVo.setpName(pName);
		}
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("menuVo", menuVo);
		return new ModelAndView(MENUEDIT,map);
	}
	@RequestMapping(value="menuEditNew")
	public ModelAndView menuEditNew(){
		String id = request.getParameter("id");
		MenuVo menuVo = null;
		if(!StringUtil.isEmpty(id)){
			menuVo = menuService.findMenuVo(id);
		}
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("menuVo", menuVo);
		return new ModelAndView(MENUEDIT,map);
	}
	/**
	 * 新增菜单
	 */
	@RequestMapping(value = "saveMenu")
	public ModelAndView saveMenu(@ModelAttribute("Menu") Menu menu){
		if(menu.getUrl()!=null && !"".equals(menu.getUrl().trim()) ){
			menu.setIsLeaf("1");
		}else{
			menu.setIsLeaf("0");
		}
		menuService.saveUpdateMenu(menu);
		return new ModelAndView(REDIRECT_MENU_LIST_ACTION);
	}
	/**
	 * 菜单列表
	 * @return
	 */
	@RequestMapping(value = "menuList")
	public ModelAndView menuList(){
		HashMap<String, Object> map = new HashMap<String, Object>();
		List<Menu> menuList = menuService.findMenuList();
		String menuListJson = JsonUtil.Object2Json(menuList);
		request.setAttribute("menuListJson",menuListJson);
		return new ModelAndView(MENUELIST);
	}
	/**
	 * 删除菜单
	 * @param menu
	 * @return
	 */
	@RequestMapping(value = "removeMenu")
	@ResponseBody
	public HashMap<String, Object> removeMenu(@ModelAttribute("id") String id){
		menuService.removeMenu(id);
		HashMap map = new HashMap<String, Object>();
		map.put("result", true);
		return map;
	}
	/**
	 * 功能菜单的分配（域和角色）
	 * @return
	 */
	@RequestMapping(value = "assignMenu")
	public void assignMenu(HttpServletRequest request,HttpServletResponse response){
		String menuIdsStr=request.getParameter("menuIdsStr");
		String roleId=request.getParameter("roleId");
		if(menuIdsStr!=null && !StringUtil.isEmpty(roleId) ){
			String[] menuIds = menuIdsStr.split(",");
			menuService.updateRoleMenuService(menuIds, roleId);
		}
		List<TreeNode> listNode=menuService.findTreeNodesByRoleId(roleId);
		try {
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().print(JsonUtil.Object2Json(listNode));  //换成这个就好了
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	

}
