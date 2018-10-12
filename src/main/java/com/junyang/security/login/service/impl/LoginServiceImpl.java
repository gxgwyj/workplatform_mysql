package com.junyang.security.login.service.impl;

import java.util.List;

import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.junyang.common.utils.StringUtil;
import com.junyang.security.dao.PersonMapper;
import com.junyang.security.login.model.LoginMsg;
import com.junyang.security.login.service.LoginService;
import com.junyang.security.model.Person;
import com.junyang.security.vo.QueryPersonVo;
@Service
@WebService(endpointInterface="com.junyang.security.login.service.LoginService")
public class LoginServiceImpl implements LoginService {
	@Autowired 
	private PersonMapper personMapper;
	
	public LoginMsg loginValidate(String code, String password) {
		LoginMsg loginMsg=new LoginMsg();
		if(StringUtil.isEmpty(code)||StringUtil.isEmpty(password)){
			loginMsg.setShowMsg("用户名或密码不能为空！");
			loginMsg.setIslogin(false);
		}else{
			QueryPersonVo queryPersonVo = new QueryPersonVo();
			queryPersonVo.setqCode(code);
			queryPersonVo.setqPassWord(password);
			List<Person> personList = personMapper.selectPersonList(queryPersonVo);
			if(personList!=null && !personList.isEmpty()){
				loginMsg.setIslogin(true);
			}else{
				loginMsg.setShowMsg("用户名或密码错误！");
				loginMsg.setIslogin(false);	
			}
		}
		return loginMsg;
	}
}
