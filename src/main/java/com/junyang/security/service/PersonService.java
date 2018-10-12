package com.junyang.security.service;

import java.util.List;
import java.util.Set;

import com.junyang.common.model.page.Page;
import com.junyang.security.model.Menu;
import com.junyang.security.model.Person;
import com.junyang.security.model.Role;
import com.junyang.security.vo.PersonVo;
import com.junyang.security.vo.QueryPersonVo;


/**
 * 人员服务类
 * @author Administrator
 *
 */
public interface PersonService {
	/**
	 * 分页查询用户信息
	 * @param page
	 * @param queryPersonVo
	 * @return
	 */
	public List<PersonVo> findPersonVoPage(Page page, QueryPersonVo queryPersonVo);
	/**
	 * 根据用户ID获得用户信息
	 * @param id
	 * @return
	 */
	public PersonVo getPersonVoById(String id);
	/**
	 * 保存用户信息
	 * @param person
	 */
	public void saveOrupdatePerson(Person person);
	/**
	 * 根据用户ID获得用户角色
	 */
	public Set<Role> getPersonRoles(String personId);
	/**
	 * 根据用户ID获取用户所拥有的权限（菜单）
	 * @param personId
	 * @return
	 */
	public Set<Menu> getPersonPermissions(String personId);
	/**
	 * 根据条件获得人员信息
	 * @param queryPersonVo
	 * @return
	 */
	List<PersonVo> getPersonVo(QueryPersonVo queryPersonVo);
	/**
	 * 根据编码查询人员信息
	 * @param code
	 * @return
	 */
	public PersonVo getPersonVoByCode(String code);
      
}
