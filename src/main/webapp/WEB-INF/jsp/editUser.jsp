<%--
  Created by IntelliJ IDEA.
  User: ivanlee
  Date: 14/4/2024
  Time: 3:00
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title>Edit User Information</title>
</head>
<body>
<h2>Edit Information ${storeUser.username}</h2>
<form:form method="POST" enctype="multipart/form-data"
modelAttribute="storeUserForm">

    <form:label path="password">Password</form:label><br/>
    <form:input type="text" path="password"/> [Dont delete the {noop}!!!]<br/><br/>

    <form:label path="fullname">Full Name</form:label><br/>
    <form:input type="text" path="fullname"/><br/><br/>

    <form:label path="email">Email</form:label><br/>
    <form:input type="text" path="email"/><br/><br/>

    <form:label path="address">Address</form:label><br/>
    <form:input type="text" path="address"/><br/><br/>

    <input type="submit" value="SaveChange"/><br/><br/>

</form:form>
<a href="<c:url value="/book" />">Return to list books</a>
</body>
</html>
