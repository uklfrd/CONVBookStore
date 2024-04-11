<%--
  Created by IntelliJ IDEA.
  User: ivanlee
  Date: 10/4/2024
  Time: 23:29
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Book Page</title>
    <jsp:include page="header.jsp"></jsp:include>
</head>
<body>

<h2>Edit Book #${book.id}</h2>
<form:form method="POST" enctype="multipart/form-data"
           modelAttribute="bookForm">
    <form:label path="bookName">Book Name</form:label><br/>
    <form:input type="text" path="bookName"/><br/><br/>
    <form:label path="author">Author</form:label><br/>
    <form:input type="text" path="author"/><br/><br/>
    <form:label path="price">Price</form:label><br/>
    <form:input type="float" path="price"/><br/><br/>
    <form:label path="description">Description</form:label><br/>
    <form:textarea path="description" rows="5" cols="30"/><br/><br/>
    <b>Add more attachments</b><br />
    <input type="file" name="attachments" multiple="multiple"/><br/><br/>
    <form:label path="state">In-stock</form:label><br/>
    <form:input type="text" path="state"/><br/><br/>
    <input type="submit" value="Save"/><br/><br/>
</form:form>
<a href="<c:url value="/book" />">Return to list books</a>
</body>
</html>

