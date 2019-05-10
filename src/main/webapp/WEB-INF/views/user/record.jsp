<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/header.jsp" />
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col">
				<jsp:include page="../include/menu.jsp" />
			</div>
			<div class="col">
				<h2>Welcome to records page ${ user.username}</h2>
				<div class="container-fluid">
					<c:forEach var="doc" items="${ documents }">
						<div class="row">
							<div class="col">${ doc.name }</div>
							<div class="col">
								<a href="${ contextPath }/Download?node=${doc.uuid}">
									Descargar </a>
							</div>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../include/footer.jsp" />
</body>
</html>