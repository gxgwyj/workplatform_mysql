package com.junyang.common.interceptor.realm;

import com.junyang.common.Constants;
import com.junyang.security.model.Menu;
import com.junyang.security.model.Role;
import com.junyang.security.service.PersonService;
import com.junyang.security.vo.PersonVo;
import com.junyang.security.vo.QueryPersonVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Realm充当了Shiro与应用安全数据间的“桥梁”或者“连接器”。也就是说，当对用户执行认证（登录）和授权（访问控制）验证时，
 * Shiro会从应用配置的Realm中查找用户及其权限信息。
 */


public class ShiroRealm extends AuthorizingRealm {

    @Autowired
    private PersonService personService;

    /**
     * 获取用户权限（授权实现）注明：由于该授权使用了缓存机制，所以该方法只有在第一次访问权限的时候执行，并且将用户的角色、权限缓存，随后访问的时候就不再执行该方法
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();//创建授权对象
        Subject currentUser = SecurityUtils.getSubject();//获取当前的用户
        Session session = currentUser.getSession();
        PersonVo personVo = (PersonVo) session.getAttribute(Constants.SESSION_USER);
        Set<String> roleStrs = new HashSet<String>();
        Set<String> permissionsStrs = new HashSet<String>();
        Set<Menu> menus = personService.getPersonPermissions(personVo.getId());
        Set<Role> roles = personService.getPersonRoles(personVo.getId());
        for (Menu menu : menus) {
            if (menu.getUrl() != null) {
                permissionsStrs.add("/" + menu.getUrl().replace("?per=menu", ""));
            }
        }
        for (Role role : roles) {
            if (role.getrId() != null) {//Set集合中不能存放null
                roleStrs.add(role.getrId());
            }
        }
        authorizationInfo.setRoles(roleStrs);
        authorizationInfo.setStringPermissions(permissionsStrs);
        return authorizationInfo;
    }

    /**
     * 获取用户信息(认证实现)
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());

        QueryPersonVo queryPersonVo = new QueryPersonVo();
        queryPersonVo.setqCode(username);
        queryPersonVo.setqPassWord(password);
        List<PersonVo> personVoList = personService.getPersonVo(queryPersonVo);

        if (personVoList != null && personVoList.size() > 0) {
            PersonVo personVo = personVoList.get(0);
            Subject subject = SecurityUtils.getSubject();//，获取当前的用户
            Session session = subject.getSession();
            session.setAttribute(Constants.SESSION_USER, personVo);
            return new SimpleAuthenticationInfo(personVo.getCode(), personVo.getPassword(), getName());
        }
        return null;
    }

}
