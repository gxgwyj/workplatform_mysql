package com.junyang.security.person.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.junyang.common.model.page.Page;
import com.junyang.security.menu.model.Menu;
import com.junyang.security.organization.model.Organization;
import com.junyang.security.organization.vo.QueryOrganizationVo;
import com.junyang.security.person.model.Person;
import com.junyang.security.person.vo.PersonVo;
import com.junyang.security.person.vo.QueryPersonVo;
import com.junyang.security.role.model.Role;

/**
 * dao 人员接口
 * @author Administrator
 *
 */

public interface PersonMapper {
	   
	   /**
	    * 条件查询用户信息列表
	    * @param Person
	    * @return
	    */
	   List<Person> selectPersonList(QueryPersonVo queryPersonVo);
	   /**
	    * Id条件删除用户信息
	    * @param PersonId
	    */
	   void deletePerson(String Id);
	   
	   /**
	    * 保存用户信息
	    * @param Person
	    */
	   void insert(Person Person); 
	   /**
	    * 分页用户
	    * @param page
	    * @param queryPersonVo
	    * @return
	    */
	   List<PersonVo> selectPersonVoPage(@Param("page")Page page,@Param("queryPersonVo")QueryPersonVo queryPersonVo);
	   /**
	    * 根据Id获取人员
	    * @param id
	    * @return
	    */
	   PersonVo selectPersonVoById(String id);
	   List<PersonVo> selectPersonVoAll(@Param("queryPersonVo")QueryPersonVo queryPersonVo);
	   /**
	    * 更新用户
	    * @param person
	    * @return
	    */
	   int updateByPrimaryKeySelective(Person person);
		
	   
	   

}
