<!DOCTYPE html>
<html>
<head>
    <title>Customer Support</title>
    <jsp:include page="header.jsp"></jsp:include>
</head>
<body>
<h2>Book #${bookId}: <c:out value="${book.author}"/></h2>
<i>Book Name - <c:out value="${book.bookName}"/></i><br/><br/>
<c:out value="${book.author}"/><br/><br/>
$
<c:out value="${book.price}"/><br/><br/>
<c:out value="${book.description}"/><br/><br/>
<c:if test="${book.numberOfAttachments > 0}">
    Attachments:
    <c:forEach items="${book.attachments}" var="attachment" varStatus="status">
        <c:if test="${!status.first}">, </c:if>
        <a href="<c:url value="/book/${bookId}/attachment/${attachment.id}" />">
            <c:out value="${attachment.name}"/></a>
    </c:forEach><br/><br/>
</c:if>
<a href="<c:url value="/book" />">Return to list books</a>
<button class="btnAddToShoppingCart-popup">Add to Shopping Cart</button>
</body>
</html>