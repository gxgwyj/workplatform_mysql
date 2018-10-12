package com.junyang.security.service;

import java.util.List;
import java.util.Set;

import com.junyang.common.model.tree.TreeNode;
import com.junyang.security.model.Menu;
import com.junyang.security.vo.MenuVo;

/**
 * 菜单服务接口
 * @author Administrator
 *
 */
public interface MenuService {
	public void saveUpdateMenu(Menu menu);
	public void changeMenu(Menu menu);
	public List<Menu> findMenuList();
	public void removeMenu(String id);
	public Menu getMenu(String id);
	public MenuVo findMenuVo(String id);
	public List<TreeNode>  findTreeNodesByRoleId(String roleId);
	public List<String>  findRoleMenusByRoleId(String roleId);
	public void updateRoleMenuService(String[] menuIds, String roleId);
	public Set<Menu> findPersonMenusByPersonId(String personId);

}
