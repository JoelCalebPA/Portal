<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../include/menu.jsp" />
	<div class="page-container">
		<p>${ user.username }</p>
		<c:forEach var="role" items="${ user.roles }">
			<p>${ role.name }</p>
		</c:forEach>
		<p>${ user.password }</p>
	</div>
	<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>