package com.junyang.security.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junyang.common.model.page.Page;
import com.junyang.common.util.StringUtil;
import com.junyang.security.dao.PersonMapper;
import com.junyang.security.model.Menu;
import com.junyang.security.model.Person;
import com.junyang.security.model.Role;
import com.junyang.security.service.MenuService;
import com.junyang.security.service.PersonService;
import com.junyang.security.service.RoleService;
import com.junyang.security.vo.PersonVo;
import com.junyang.security.vo.QueryPersonVo;
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired 
    private PersonMapper personMapper;
    @Autowired 
    private RoleService roleService;
    @Autowired 
    private MenuService menuService;
	@Override
	public List<PersonVo> findPersonVoPage(Page page, QueryPersonVo queryPersonVo) {
		List<PersonVo> records = personMapper.selectPersonVoPage(page,queryPersonVo);
		return records;
	}
	@Override
	public PersonVo getPersonVoById(String id) {
		PersonVo personVo = personMapper.selectPersonVoById(id);
		return personVo;
	}
	@Override
	public void saveOrupdatePerson(Person person) {
		if(StringUtil.isEmpty(person.getId())){
			personMapper.insert(person);
		}else{
			personMapper.updateByPrimaryKeySelective(person);
		}
		
	}
	@Override
	public void removePersons(String[] ids) {
		if(ids!=null && ids.length>0){
			for(String id:ids){
				personMapper.deletePerson(id);
			}
		}
	}
	@Override
	public Set<Role> getPersonRoles(String personId) {
		return roleService.findPersonRoleByPersonId(personId);
	}
	public Set<Menu> getPersonPermissions(String personId) {
		return menuService.findPersonMenusByPersonId(personId);
	}
	@Override
	public List<PersonVo> getPersonVo(QueryPersonVo queryPersonVo) {
		try {
			personMapper.selectPersonVoAll(queryPersonVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return personMapper.selectPersonVoAll(queryPersonVo);
	}
}