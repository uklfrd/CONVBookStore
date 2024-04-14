<html>
<%--
<style>

    header {
        position: fixed;
        top: 0;
        left: 0;
        width: 100%;
        padding: 20px 100px;
        background: dodgerblue;
        display: flex;
        justify-content: space-between;
        align-items: center;
        z-index: 99;
    }

    .Logo{
        font-size: 2em;
        color: #ffffff;
        user-select: none;
    }

    .navigation a {
        position: relative;
        font-size: 1.5em;
        color: #ffffff;
        text-decoration: none;
        font-weight: 500;
        margin-left: 40px;
    }

    .navigation a:after {
        content: ' ';
        position: absolute;
        left: 0;
        bottom: -6px;
        width: 100%;
        height: 3px;
        background: #ffffff;
        border-radius: 5px;
        transform: scaleX(0);
        transition: transform 0.5s;
    }

    .navigation a:hover:after {
        transform: scaleX(1);
    }

</style>
--%>
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
        <a href="<c:url value="/book?action=viewCart" />">Cart</a>
        </security:authorize>

        <security:authorize access="hasRole('ADMIN') || hasRole('USER')">
        <a href="<c:url value="/book/create" />">Create sell</a><br/>
        </security:authorize>

        <security:authorize access="hasRole('ADMIN')">
            <a href="<c:url value="/user" />">User</a>
        </security:authorize>

        <security:authorize access="!hasRole('ADMIN') && !hasRole('USER')">
            <a href="<c:url value="/user/create" />">Sign Up</a>
        </security:authorize>

        <security:authorize access="hasRole('ADMIN') || hasRole('USER')">
        <c:url var="logoutUrl" value="/logout"/>
        <form action="${logoutUrl}" method="post">
            <input type="submit" value="Log out" />
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        </security:authorize>
        <security:authorize access="hasRole('UNREG')">
            <c:url var="logoutUrl" value="/logout"/>
            <form action="${logoutUrl}" method="post">
                <input type="submit" value="Guset Log out" />
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </security:authorize>


    </nav>
</header>
</body>
</html>


