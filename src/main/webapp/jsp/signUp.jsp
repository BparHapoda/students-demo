<%--
  Created by IntelliJ IDEA.
  User: julia
  Date: 17.03.2023
  Time: 13:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h1 style="color: ${color}"> Registration page </h1>
<h2>Enter your data :</h2>
<form action="signUp" method="post">
<pre>
    <label for="firstName">Enter your first name</label>
    <input id="firstName" name="firstName" type="text"/>

    <label for="lastName">Enter your last name</label>
    <input id="lastName" name="lastName" type="text"/>

    <label for="email">Enter your email</label>
    <input id="email" name="email" type="email"/>

    <label for="password">Enter your password</label>
    <input id="password" name="password" type="password"/>


    <input type="submit"/>
</pre>
</form>
</body>
</html>
