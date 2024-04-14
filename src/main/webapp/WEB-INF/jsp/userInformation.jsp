<%--
  Created by IntelliJ IDEA.
  User: ivanlee
  Date: 14/4/2024
  Time: 1:32
  To change this template use File | Settings | File Templates.
--%>

<html>
<head>
    <title>User Information</title>
    <jsp:include page="header.jsp"></jsp:include>
</head>
<body>

<h2>User Name: ${storeUser.username}</h2>

<security:authorize access="hasRole('ADMIN') or
 principal.username=='${storeUser.username}'">
    [<a href="<c:url value="/user/edit/${storeUser.username}" />">Edit</a>]
</security:authorize>

<security:authorize access="hasRole('ADMIN')">
    [<a href="<c:url value="/user/delete/${storeUser.username}" />">Delete</a>]
</security:authorize>
<br/><br/>

<i>User Name - <c:out value="${storeUser.username}"/></i><br/><br/>

<c:out value="${fn:substringAfter(storeUser.password, '{noop}')}"/><br/><br/>
<c:out value="${storeUser.fullname}"/><br/><br/>
<c:out value="${storeUser.email}"/><br/><br/>
<c:out value="${storeUser.address}"/><br/><br/>


<a href="<c:url value="/book" />">Return to list books</a>
<security:authorize access="hasRole('ADMIN')">
    <a href="<c:url value="/user" />">Return to Manage User Accounts</a><br/>
</security:authorize>
</body>
</html>
