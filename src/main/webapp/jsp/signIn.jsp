<%--
  Created by IntelliJ IDEA.
  User: julia
  Date: 18.03.2023
  Time: 0:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignIn</title>
</head>
<body>
<form action="signIn" method="post">
  <h1 style="color: ${color}">Sign In</h1>
  <input type="email" name="email" placeholder="email">
  <input type="password" name="password" placeholder="password">
  <input type="submit">
</form>
</body>
</html>
