package com.junyang.common.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.activiti.engine.ProcessEngines;

/**
 * Servlet环境下初始化和销毁流程引擎
 * @author Administrator
 *
 */
public class ProcessEnginesServletContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent arg0)  { 
    	 ProcessEngines.init();
    }
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	ProcessEngines.destroy();
    }
	
}
