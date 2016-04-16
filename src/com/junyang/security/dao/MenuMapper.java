package com.junyang.security.dao;

import java.util.List;
import java.util.Set;

import com.junyang.common.model.tree.TreeNode;
import com.junyang.security.model.Menu;
import com.junyang.security.vo.MenuVo;

/**
 * 菜单dao接口
 * @author Administrator
 *
 */
public interface MenuMapper {
	/**
	 * 增加菜单
	 * @param Menu
	 */
	 void insertMenu(Menu menu);
	 /**
	  * 修改菜单
	  * @param menu
	  */
	 void updateMenu(Menu menu);
	 /**
	  * 查询菜单
	  * @return
	  */
	 List<Menu>  selectMenus();
	 /**
	  * 根据id删除菜单
	  * @param id
	  */
	 void deleteMenu(String id);
	 /**
	  * 根据id获得特定的菜单
	  * @param id
	  * @return
	  */
	 Menu selectMenu(String id);
	 /**
	  * 根据ID获取菜单
	  * @param id
	  * @return
	  */
	 List<MenuVo> selectMenuVo(String id);
	 /**
	  * 更新菜单
	  * @param menu
	  */
	 void updateByPrimaryKeySelective(Menu menu);
	 /**
	  * 树形菜单查询
	  * @param roleId
	  * @return
	  */
	 List<TreeNode> selectTreeNodesByRoleId(String roleId);
	 /**
	  * 根据角色ID查询角色拥有的权限（菜单）
	  * @param roleId
	  * @return
	  */
	 List<String> selectRoleMenuIdsByRoleId(String roleId);
	 /**
	  * 根据人员ID查询用户所拥有的权限（菜单）
	  * @param personId
	  * @return
	  */
	 Set<Menu> selectPersonMenuIdByPersonId(String personId);

}
