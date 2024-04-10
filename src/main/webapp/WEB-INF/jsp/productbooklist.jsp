<!DOCTYPE html>
<html>
<head>
    <title>Book List</title>
    <jsp:include page="header.jsp"></jsp:include>
</head>
<body>
<h2>Books</h2>

<a href="<c:url value="/book/create" />">Create a book sell</a><br/><br/>
<c:choose>
    <c:when test="${fn:length(bookDatabase) == 0}">
        <i>There are no books in the system.</i>
    </c:when>
    <c:otherwise>
        <c:forEach items="${bookDatabase}" var="entry">
            Book ${entry.id}:
            <a href="<c:url value="/book/view/${entry.id}" />">
                <c:out value="${entry.bookName}"/></a>
            (Author: <c:out value="${entry.author}"/>)<br />
            [<a href="<c:url value="/book/delete/${entry.id}" />">Delete</a>]<br />

        </c:forEach>
    </c:otherwise>
</c:choose>
</body>
</html>