package com.junyang.template.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
@Controller
@RequestMapping("springTemplate/")
public class SpringController {
	//请求URL地址映射，类似Struts的action-mapping
	@RequestMapping(value="springMVCDemo.do",method=RequestMethod.GET)
    public ModelAndView springDemo(){
		//采用重定向方式跳转页面
        return new ModelAndView("index"); 
    }


}
