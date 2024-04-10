<%--
  Created by IntelliJ IDEA.
  User: ivanlee
  Date: 10/4/2024
  Time: 22:38
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head><title>CONV User Management</title></head>
<body>
<jsp:include page="header.jsp"></jsp:include>

<br /><br />
<a href="<c:url value="/book" />">Return to list books</a>
<h2>Users</h2>
<a href="<c:url value="/user/create" />">Create a User</a><br /><br />
<c:choose>
    <c:when test="${fn:length(storeUsers) == 0}">
        <i>There are no users in the system.</i>
    </c:when>
    <c:otherwise>
        <table>
            <tr>
                <th>Username</th><th>Password</th><th>Roles</th><th>Action</th>
            </tr>
            <c:forEach items="${storeUsers}" var="user">
                <tr>
                    <td>${user.username}</td>
                    <td>${fn:substringAfter(user.password, '{noop}')}</td>
                    <td>
                        <c:forEach items="${user.roles}" var="role" varStatus="status">
                            <c:if test="${!status.first}">, </c:if>
                            ${role.role}
                        </c:forEach>
                    </td>
                    <td>
                        [<a href="<c:url value="/user/delete/${user.username}" />">Delete</a>]
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:otherwise>
</c:choose>
</body>
</html>
