package com.junyang.test;

import com.junyang.common.model.tree.MyPage;
import com.junyang.security.service.PersonService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 类: PersonServiceTest <br>
 * 描述: 用户管理单元测试<br>
 * 作者:  gaoxugang<br>
 * 时间: 2018年10月18日 16:54
 */
public class PersonServiceTest extends BaseTest {

    @Autowired
    PersonService personService;

    @Test
    public void testFindPersonVoPage() {
        MyPage page = new MyPage();
        personService.findPersonVoPage(page, null);
    }
}
