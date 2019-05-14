<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/header.jsp" />
</head>
<body class="animsition">
	<div class="page-wrapper">
		<jsp:include page="../include/menu.jsp" />
		<div class="page-container2" style="margin-left:25%">
			<div class="section__content section__content--p30">
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