<%--
  Created by IntelliJ IDEA.
  User: ivanlee
  Date: 11/4/2024
  Time: 20:17
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>Shopping Cart</title>
    <jsp:include page="header.jsp"></jsp:include>
</head>
<body>
<h1>View Cart</h1>

<c:choose>
    <c:when test="${empty cart}">
        Your cart is empty
        <br />
    </c:when>
    <c:otherwise>
        <ul>
            <c:forEach items="${cart}" var="cartItem" >
                <li>Book ID: ${cartItem.key} (qty: ${cartItem.value})</li>
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>
<a href="<c:url value="/book?action=emptyCart" />">Empty Cart</a><br/><br/>
<a href="<c:url value="/book" />">back to the Book List</a><br/><br/>
</body>

<script>

</script>
</html>