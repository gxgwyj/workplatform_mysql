package com.junyang.security.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.junyang.common.Constants;
import com.junyang.common.model.page.Page;
import com.junyang.common.utils.StringUtil;
import com.junyang.security.model.Organization;
import com.junyang.security.model.Person;
import com.junyang.security.service.OrganizationService;
import com.junyang.security.service.PersonService;
import com.junyang.security.vo.PersonVo;
import com.junyang.security.vo.QueryPersonVo;

@Controller
@RequestMapping(value="security/person/")
public class PersonController {
    private static final String PERSON_EDIT_VIEW="security/person/personEdit";
    private static final String PERSON_LIST_VIEW="security/person/personList";
    private static final String PERSON_LIST_ACTION="security/person/personList.do";
    private static final String REDIRECT_PERSON_LIST_ACTION="redirect:personList.do";
    @Autowired
    private PersonService personService;
    @Autowired
    private OrganizationService organizationService;
	
	/**
	 * 跳转至编辑页面
	 * @return
	 */
	@RequestMapping(value="personEdit")
	private ModelAndView personEdit(HttpServletRequest  request){
		String id = request.getParameter("id");
		PersonVo personVo = null;
		if(!StringUtil.isEmpty(id)){
			personVo = personService.getPersonVoById(id);
		}
		List<Organization> organizationList = organizationService.findOrganizationList();
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("personVo", personVo);
		map.put("organizationList", organizationList);
		return new ModelAndView(PERSON_EDIT_VIEW,map);
	}
	/**
	 * 人员列表页面
	 * @return
	 */
	@RequestMapping(value="personList")
	private ModelAndView personList(@ModelAttribute("queryPersonVo") QueryPersonVo queryPersonVo,HttpServletRequest request,@ModelAttribute("page") Page page){
		if(!Constants.TURN_PAGE.equals(request.getParameter(Constants.TURN_PAGE))){
			page.setPageNo(1);
		}
		page.initTurnPageUrl(PERSON_LIST_ACTION);
		List<PersonVo> personVoList = personService.findPersonVoPage(page,queryPersonVo);
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("personVoList", personVoList);
		map.put("page", page);
		map.put("queryPersonVo", queryPersonVo);
		List<Organization> organizationList = organizationService.findOrganizationList();
		map.put("organizationList", organizationList);
		return new ModelAndView(PERSON_LIST_VIEW,map);
	}
	/**
	 * @return
	 */
	@RequestMapping(value="savePerson")
	@ResponseBody
	private ModelAndView savePerson(HttpServletRequest request,@ModelAttribute("person") Person person,@RequestParam(value = "personImg", required = false) MultipartFile file){
		String originalName = file.getOriginalFilename();//原文件名称
		if(!StringUtil.isEmpty(originalName)){
			//删除原来的图片
			File oldImg = new File(person.getImg());
			if(oldImg.exists()){
				oldImg.delete();
			}
			String projectName = request.getContextPath().replaceAll("/", "");//项目名称
			UUID uuid = UUID.randomUUID();
			//要保存的文件名称
			String fileName =  uuid.toString().replaceAll("-","")+originalName.substring(originalName.lastIndexOf("."));;
			String path = "D:\\"+projectName;
		    File targetFile = new File(path, fileName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
		  try {  
	            file.transferTo(targetFile);//保存文件 
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }
			person.setImg(path+"\\"+fileName);  
		}
		personService.saveOrupdatePerson(person);
		return new ModelAndView(REDIRECT_PERSON_LIST_ACTION);
	}
	

}
