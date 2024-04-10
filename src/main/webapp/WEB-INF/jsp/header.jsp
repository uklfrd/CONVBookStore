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
    <link rel="stylesheet" href="style.css">
</head>
<body>
<header>
    <h1 class="Logo">CONVBookStore</h1>
    <nav class="navigation">
        <a href="<c:url value="/book" />">Home</a>
        <a href="<c:url value="/shoppingCart" />">Shopping Cart</a>

        <security:authorize access="!hasRole('ADMIN') && !hasRole('USER')">
            <button class="btnLogin-popup">Login</button>
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


