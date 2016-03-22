package com.zl.system.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SystemRealm重写Shiro认证授权方法
 * 
 * @author monee 2015-09-30
 */
public class SystemRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(SystemRealm.class);

	/**
	 * 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		/*
		 * // 获取当前登陆的用户名 String loginName = (String)
		 * principalCollection.fromRealm(getName()).iterator().next(); //
		 * 根据用户名查找对象 User user = userService.findByLoginName(loginName); if
		 * (user != null) { SimpleAuthorizationInfo info = new
		 * SimpleAuthorizationInfo(); // 添加角色(Set集合<字符串>)
		 * info.setRoles(user.getGroupNameSet()); // 迭代用户对应的角色集合，为了获取角色对应的权限 for
		 * (UserGroup g : user.getUserGroupList()) { // 添加permission
		 * info.addStringPermissions(g.getPermissionStringList()); } return
		 * info; }
		 */
		return null;
	}

	/**
	 * 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {

		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String userName = token.getUsername();
		// User userName = userService.findByLoginName(token.getUsername());
		logger.debug("……………………………………………………登录验证中……………………………………………………");
		if (userName != null && !"".equals(userName)) {
			logger.debug(token.getUsername());
			logger.debug(String.valueOf(token.getPassword()));
			return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());
		}
		return null;
	}

}
