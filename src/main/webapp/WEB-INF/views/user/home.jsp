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
		<iframe id="preview-doc" src="${ currentDoc.downloadUrl }"
			style="width: 99%; border: 0px; height: 99%; display: inline;"></iframe>
	</div>
	<jsp:include page="../include/footer.jsp" />
</body>
</html>