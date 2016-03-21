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
		logger.debug("……………………………………………………登录验证中……………………………………………………");
		if (userName != null && !"".equals(userName)) {
			logger.debug(token.getUsername());
			logger.debug(String.valueOf(token.getPassword()));
			return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());
		}
		return null;
	}

}
