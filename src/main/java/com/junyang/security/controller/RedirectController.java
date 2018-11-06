package com.junyang.security.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 类: RedirectController <br>
 * 描述: 页面转发<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年11月06日 18:46
 */
@Controller
public class RedirectController {

    @RequestMapping(value = "/toPage")
    public ModelAndView toPage(String path) {
        ModelAndView mv = new ModelAndView();
        mv.setViewName(path);
        return mv;
    }
}
