<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>Welcome to records page ${pageContext.request.userPrincipal.name}</h2>
<ul>
<c:forEach var="rec" items="${ records }">
<li>${ rec.path.substring(rec.path.lastIndexOf("/")+1) } --- ${ rec.uuid }</li>
</c:forEach>
</ul>
</body>
</html>