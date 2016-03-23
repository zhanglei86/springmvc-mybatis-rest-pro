<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆成功</title>
</head>
<body>
	<h2>${msg}</h2>
	<p>
		<a href="logout">loginOut</a>
	</p>

	<div>
		<div id="Test1">
			<h3>1,shiro:guest,验证当前用户是否为“访客”，即未认证（包含未记住）的用户</h3>
			<shiro:guest>  
    Hi there!  Please <a href="login">Login</a> or <a href="signup.jsp">Signup</a> today!  
	</shiro:guest>
		</div>
		<div id="Test2">
			<h3>2,shiro:user,认证通过或已记住的用户</h3>
			<shiro:user>
    Welcome back John!  Not John? Click <a href="login">here</a>to
				login. 
	</shiro:user>
		</div>

		<div id="Test3">
			<h3>3,shiro:authenticated,已认证通过的用户。不包含已记住的用户，这是与user标签的区别所在。</h3>
			<shiro:authenticated>
				<a href="updateAccount.jsp">Update your contact information</a>.</shiro:authenticated>
		</div>

		<div id="Test4">
			<h3>4,shiro:notAuthenticated,未认证通过用户，与authenticated标签相对应。与guest标签的区别是，该标签包含已记住用户。</h3>
			<shiro:notAuthenticated>Please <a
					href="login">login</a> in order to update your credit card information.  </shiro:notAuthenticated>
		</div>

		<div id="Test5">
			<h3>5,shiro:principal,输出当前用户信息，通常为登录帐号信息</h3>
			Hello,
			<shiro:principal />
			, how are you today?
		</div>

		<div id="Test6">
			<h3>6,shiro:hasRole,验证当前用户是否属于该角色</h3>
			<shiro:hasRole name="administrator">
				<a href="admin.jsp">Administer the system</a>
			</shiro:hasRole>
		</div>

		<div id="Test7">
			<h3>7,shiro:lacksRole,与hasRole标签逻辑相反，当用户不属于该角色时验证通过</h3>
			<shiro:lacksRole name="administrator">Sorry, you are not allowed to administer the system.</shiro:lacksRole>
		</div>

		<div id="Test8">
			<h3>8,shiro:hasAnyRoles,验证当前用户是否属于以下任意一个角色。</h3>
			<shiro:hasAnyRoles name="developer, project manager, administrator">
	You are either a developer, project manager, or administrator.
	</shiro:hasAnyRoles>
		</div>

		<div id="Test9">
			<h3>9,shiro:hasPermission,验证当前用户是否拥有制定权限</h3>
			<shiro:hasPermission name="user:create">
				<a href="createUser.jsp">Create a new User</a>
			</shiro:hasPermission>
		</div>

		<div id="10">
			<h3>10,shiro:hasPermission,与hasPermission标签逻辑相反，当前用户没有制定权限时，验证通过</h3>
			<shiro:hasPermission name="user:create">
				<a href="createUser.jsp">Create a new User</a>
			</shiro:hasPermission>
		</div>
	</div>
</body>
</html>