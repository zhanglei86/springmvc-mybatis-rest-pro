package win.leizhang.system.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Realm重写Shiro认证授权方法
 * 
 * @author zl 2016-03-22
 */
public class UserRealm extends AuthorizingRealm {

	private Logger logger = LoggerFactory.getLogger(UserRealm.class);

	/**
	 * 授权信息
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

		/** 1 **/

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

		/** 2 **/

		/*
		 * String username = (String) principalCollection.getPrimaryPrincipal();
		 * SimpleAuthorizationInfo authorizationInfo = new
		 * SimpleAuthorizationInfo(); User user = userService.getUser(username);
		 * Set<Role> uroles = user.getRoles(); Set<String> perms = new
		 * HashSet<String>(); for (Role role : uroles) { Set<Resource> resources
		 * = role.getResources(); for (Resource resource : resources) { Object
		 * permission = resource.getPerms(); if (permission == null ||
		 * StringUtils.isEmpty(permission.toString())) { continue; }
		 * perms.add(StringUtils.substringBetween(permission.toString(),
		 * "perms[", "]")); } } authorizationInfo.setStringPermissions(perms);
		 * return authorizationInfo;
		 */

		return null;
	}

	/**
	 * 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken)
			throws AuthenticationException {
		// 1. 接受提交的当事人和证书：
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		String userName = token.getUsername();
		String passWord = String.valueOf(token.getPassword());

		/*
		 * String userName=(String)authcToken.getPrincipal(); User user =
		 * userService.findByLoginName(userName); if (user == null) { throw new
		 * UnknownAccountException(); } if (user.getStatus() ==
		 * Constants.USER_STATUS_LOCKED) { throw new LockedAccountException(); }
		 */
		logger.debug("……………………………………………………登录验证中……………………………………………………");
		if (userName != null && !"".equals(userName)) {
			logger.debug("userName:" + userName);
			logger.debug("passWord:" + passWord);
			if (userName.equals("zhanglei") && passWord.equals("zhanglei")) {
				return new SimpleAuthenticationInfo(token.getUsername(), token.getPassword(), getName());
			} else {
				throw new UnknownAccountException("No account found for user [" + userName + "]");
			}
		}
		throw new AccountException();
	}

}
