<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta>
<jsp:include page="../include/header.jsp" />
</head>
<body>
	<jsp:include page="../include/menu.jsp" />

	<div class="page-container">
		<h2>Welcom to da home page ma boi
			${pageContext.request.userPrincipal.name}</h2>
		<c:forEach var="role" items="${ user.roles }">
			<p>${ role.name }</p>
		</c:forEach>

		<h3>here is your document: ${ currentDoc.name }</h3>
		<h3>here is your preview:</h3>
		<embed showcontrols="false"
			src="${ contextPath }/Preview?node=${ currentDoc.uuid }"
			type="application/pdf" width="100%" height="350">
	</div>
	<jsp:include page="../include/footer.jsp" />
</body>
</html>