<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/header.jsp"></jsp:include>
</head>
<body class="animsition">
	<div class="page-wrapper">
		<jsp:include page="../include/menu.jsp" />
		<div class="page-container2" style="margin-left:25%">
			<div class="section__content section__content--p30">
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