<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录</title>
</head>
<body>
	<div>
		<form action="<%=request.getContextPath()%>/auth/login" method="POST">
			<div>
				用户名:<input name="j_username" id="id_j_username" type="text">
			</div>
			<div>
				密&nbsp;码:<input name="j_password" id="id_j_password" type="password">
			</div>
			<div>
				<button type="submit">OK</button>
			</div>
			<div>${msg}</div>
		</form>
	</div>
</body>
</html>