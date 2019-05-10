<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/header.jsp"></jsp:include>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<jsp:include page="../include/menu.jsp"></jsp:include>
			</div>
			<div class="col">
				<p>${ user.username }</p>
				<c:forEach var="role" items="${ user.roles }">
					<p>${ role.name }</p>
				</c:forEach>
				<p>${ user.password }</p>
			</div>
		</div>
	</div>
	<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>