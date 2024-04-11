<!DOCTYPE html>
<html>
<head>
    <title>Book information</title>
    <jsp:include page="header.jsp"></jsp:include>
</head>
<body>
<h2>Book #${bookId}: <c:out value="${book.bookName}"/></h2>

<security:authorize access="hasRole('ADMIN') or
 principal.username=='${book.author}'">
    [<a href="<c:url value="/book/edit/${book.id}" />">Edit</a>]
</security:authorize>
<security:authorize access="hasRole('ADMIN')">
    [<a href="<c:url value="/book/delete/${book.id}" />">Delete</a>]
</security:authorize>
<br/><br/>

<i>Book Name - <c:out value="${book.bookName}"/></i><br/><br/>
<c:out value="${book.author}"/><br/><br/>
<c:out value="$${book.price}"/><br/><br/>
<c:out value="${book.description}"/><br/><br/>
<c:if test="${!empty book.attachments}">
    Attachments:
    <c:forEach items="${book.attachments}" var="attachment" varStatus="status">
        <c:if test="${!status.first}">, </c:if>
        <a href="<c:url value="/book/${bookId}/attachment/${attachment.id}" />">
            <c:out value="${attachment.name}"/></a>
        [<a href="<c:url value="/book/${bookId}/delete/${attachment.id}" />">Delete</a>]

    </c:forEach><br/><br/>
</c:if>
In-stock:<c:out value="${book.state}"/><br/><br/>

[<a href="<c:url value="/book">
                   <c:param name="action" value="addToCart" />
                   <c:param name="productId" value="${book.id}" />
                </c:url>">AddToCart</a>]

<a href="<c:url value="/book" />">Return to list books</a>
</body>
</html>