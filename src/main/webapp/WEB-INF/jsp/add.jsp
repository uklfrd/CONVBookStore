<!DOCTYPE html>
<html>
<head>
    <title>Add Book Sell</title>
    <jsp:include page="header.jsp"></jsp:include>
</head>
<body>
<h2>Create a Book</h2>
<form:form method="POST" enctype="multipart/form-data" modelAttribute="bookForm">
    <form:label path="bookName">Book Name</form:label><br/>
    <form:input type="text" path="bookName"/><br/><br/>
    <form:label path="Author">Author</form:label><br/>
    <form:input type="text" path="Author"/><br/><br/>
    <form:label path="price">Price</form:label><br/>
    <form:input type="float" path="price"/><br/><br/>
    <form:label path="description">Description</form:label><br/>
    <form:textarea path="description" rows="5" cols="30"/><br/><br/>
    <b>Attachments</b><br/>
    <input type="file" name="attachments" multiple="multiple"/><br/><br/>
    <input type="submit" value="Submit"/>
</form:form>
</body>
</html>
