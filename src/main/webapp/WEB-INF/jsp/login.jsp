<%--
  Created by IntelliJ IDEA.
  User: ivanlee
  Date: 10/4/2024
  Time: 23:31
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>CONV Login</title>
</head>
<body>
<c:if test="${param.error != null}">
    <p>Login failed.</p>
</c:if>
<c:if test="${param.logout != null}">
    <p>You have logged out.</p>
</c:if>
<h2>CONV Login</h2>
<form action="login" method="POST">
    <label for="username">Username:</label><br/>
    <input type="text" id="username" name="username"/><br/><br/>
    <label for="password">Password:</label><br/>
    <input type="password" id="password" name="password"/><br/><br/>
    <input type="checkbox" id="remember-me" name="remember-me"/>
    <label for="remember-me">Remember me</label><br/><br/>
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="Log In"/>

</form>
<c:url var="loginUrl" value="/login"/>

<p>If you are GUSET, can use this Username & password:</p><br/>
<p>Username: anyone</p><br/>
<p>password: anyonepw</p><br/>

</form>
</body>
</html>
