<%--
  Created by IntelliJ IDEA.
  User: ivanlee
  Date: 10/4/2024
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head><title>Add User Pages</title></head>
<body>

<jsp:include page="header.jsp"></jsp:include>

<h2>Create a User</h2>
<form:form method="POST" modelAttribute="storeUser">
    <form:label path="username">Username</form:label><br/>
    <form:input type="text" path="username"/><br/><br/>
    <form:label path="password">Password</form:label><br/>
    <form:input type="text" path="password"/><br/><br/>
    <form:label path="roles">Roles</form:label><br/>
    <form:checkbox path="roles" value="ROLE_USER"/>ROLE_USER

    <security:authorize access="hasRole('ADMIN')">
    <form:checkbox path="roles" value="ROLE_ADMIN"/>ROLE_ADMIN
    </security:authorize>
    <br/><br/>
    <input type="submit" value="Add User"/>
</form:form>
</body>
</html>
