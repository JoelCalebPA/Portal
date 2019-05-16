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
		<div class="account-header">
			<div class="form-group mb-2">
				<label>Informaci�n</label>
			</div>
		</div>
		<div class="account-information">
			<div class="form-group mb-2">
				<label>Nombre: </label> <label>${ user.username }</label>
			</div>
			<div class="form-group mb-2">
				<label>Usuario: </label>
				<c:forEach var="role" items="${ user.roles }">
					<label>${ role.name }</label>
				</c:forEach>
			</div>
		</div>
		<div class="account-header">
			<div class="form-group mb-2">
				<label>Cambiar Contrase�a</label>
			</div>
		</div>
		<div class="account-information">
			<div class="form-group mb-2">
				<label>Actual contrase�a: </label> <input class="form-control"
					type="password" />
			</div>
			<div class="form-group mb-2">
				<label>Nueva contrase�a: </label> <input class="form-control"
					type="password" />
			</div>
			<div class="form-group mb-2">
				<input type="button" class="btn btn-primary" value="Cambiar" />
			</div>
		</div>
	</div>
	<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>