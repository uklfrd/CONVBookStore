<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: ivanlee
  Date: 5/4/2024
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Header</title>
    <link href="/resources/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<header>
    <h1 class="Logo">CONVBookStore</h1>
    <nav class="navigation">
        <a href="<c:url value="/book" />">Home</a>

        <security:authorize access="hasRole('USER')">
        <a href="<c:url value="/book?action=viewCart" />">Shopping Cart</a>
        </security:authorize>
        <security:authorize access="hasRole('ADMIN') || hasRole('USER')">
        <a href="<c:url value="/book/create" />">Create a book sell</a><br/><br/>
        </security:authorize>

        <security:authorize access="!hasRole('ADMIN') && !hasRole('USER')">
            <button class="btnLogin-popup">Login</button>
            <a href="<c:url value="/user/create" />">Sign Up</a><br /><br />
        </security:authorize>

        <security:authorize access="hasRole('ADMIN')">
            <a href="<c:url value="/user" />">Manage User Accounts</a><br /><br />
        </security:authorize>

        <security:authorize access="hasRole('ADMIN') || hasRole('USER')">
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        </security:authorize>

    </nav>
</header>
</body>
</html>


