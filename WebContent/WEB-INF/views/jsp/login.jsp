<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Spring Security</title>
</head>
<body>
    <h1>Login page.</h1>
    <form action="login" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username">
        <br>
        <label for="domain">Domain:</label>
        <input type="text" name="domain" id="domain">
        <br>
        <label for="password">Password:</label>
        <input type="text" name="password" id="password">

        <br>
        <input type="submit" value="Login">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
</body>
</html>