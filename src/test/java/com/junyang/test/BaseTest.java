package com.junyang.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 类: BaseTest <br>
 * 描述: 单元测试基类<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年10月18日 16:33
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"})
public class BaseTest {
}
