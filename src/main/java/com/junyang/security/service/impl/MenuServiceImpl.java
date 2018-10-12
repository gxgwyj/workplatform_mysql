package com.junyang.security.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junyang.common.Constants;
import com.junyang.common.model.tree.TreeNode;
import com.junyang.common.utils.StringUtil;
import com.junyang.security.dao.MenuMapper;
import com.junyang.security.dao.PrivilegeMapper;
import com.junyang.security.model.Menu;
import com.junyang.security.model.Privilege;
import com.junyang.security.service.MenuService;
import com.junyang.security.vo.MenuVo;
@Service
public class MenuServiceImpl implements MenuService {
	@Autowired
    private MenuMapper menuMapper;
	@Autowired
    private PrivilegeMapper privilegeMapper;
    
    
    
	@Override
	public void saveUpdateMenu(Menu menu) {
		if(StringUtil.isEmpty(menu.getId())){
			menuMapper.insertMenu(menu);
		}else{
			menuMapper.updateByPrimaryKeySelective(menu);
		}
	}

	@Override
	public void changeMenu(Menu menu) {
		menuMapper.updateMenu(menu);
	}

	@Override
	public List<Menu> findMenuList() {
		List<Menu> menuList=menuMapper.selectMenus();
		return menuList;
	}
	@Override
	public void removeMenu(String id) {
		menuMapper.deleteMenu(id);
	}

	@Override
	public Menu getMenu(String id) {
		Menu  menu=menuMapper.selectMenu(id);
		return menu;
	}

	@Override
	public MenuVo findMenuVo(String id) {
		MenuVo menuVo = null;
		List<MenuVo> list = menuMapper.selectMenuVo(id);
		if(list!=null && list.size()>0){
			menuVo = list.get(0);
		}
		return menuVo;
	}

	@Override
	public List<TreeNode> findTreeNodesByRoleId(String roleId) {
		return menuMapper.selectTreeNodesByRoleId(roleId);
	}
	public void updateRoleMenuService(String[] menuIds, String roleId) {
		//删除已经不存在的权限
		if(menuIds!=null&&menuIds.length>0){
			int result = privilegeMapper.deleteByRoleIdAndMenuIds(menuIds, roleId);
			//最新的菜单
			List<String> NewMenus=Arrays.asList(menuIds);
			//查询域之前的权限菜单
			List<String> listMenuIds=findRoleMenusByRoleId(roleId);
		    ArrayList<String>  oldMenus=new ArrayList<String>();
		    if(listMenuIds!=null){
		    	 for(int i=0;i<listMenuIds.size();i++){
				    	oldMenus.add(listMenuIds.get(i));
				 }
		    }
		    //求差集(A->B)即新的菜单
		    ArrayList<String>  arrayContent=new ArrayList<String>();
		    arrayContent.clear();
		    arrayContent.addAll(NewMenus);
	        arrayContent.removeAll(oldMenus);
		    
	 		//保存新的权限
	 		if(arrayContent.size()>0){
	 			Privilege privilege=null;
				List<Privilege> privilegeList=new ArrayList<Privilege>();
				for(int i=0;i<arrayContent.size();i++){
					privilege=new Privilege();
					privilege.setpMid(arrayContent.get(i));
					privilege.setpMaster(Constants.P_MASTER_ROLE);
					privilege.setpMastervalue(roleId);
					privilegeList.add(privilege);
					privilegeMapper.insert(privilege);
				}
			}
		}
	
	}

	@Override
	public List<String> findRoleMenusByRoleId(String roleId) {
		return menuMapper.selectRoleMenuIdsByRoleId(roleId);
	}

	@Override
	public Set<Menu> findPersonMenusByPersonId(String personId) {
		return menuMapper.selectPersonMenuIdByPersonId(personId);
	}

}
