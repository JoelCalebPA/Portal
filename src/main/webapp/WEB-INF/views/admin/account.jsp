<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/header.jsp"></jsp:include>
</head>
<body>
	<jsp:include page="../include/menu.jsp" />
	<div class="page-container">
		<div class="record-filter">
			<form class="form-inline">
				<div class="form-group mb-2">
					<label>Nombre</label>
				</div>
				<div class="form-group mx-sm-3 mb-2">
					<input type="text" class="form-control" id="name" name="name"
						placeholder="Por ejemplo: Caleb" autofocus="autofocus">
					<button type="submit" class="form-group btn btn-primary mb-2"
						style="background-color: #ffdd00; color: #2d4191;">Buscar</button>
				</div>
			</form>
		</div>
		<div class="record-results">
			<div>
				<label>Se han encontrado ${ num } usuarios.</label>
				<button>
					<i class="fa fa-user" aria-hidden="true"></i> Crear usuario
				</button>
			</div>
			<div class="table-responsive">
				<table class="table">
					<thead>
						<tr>
							<th>Sede</th>
							<th>Apellidos y Nombres</th>
							<th>Usuario</th>
							<th></th>
							<th></th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="u" items="${ users }">
							<tr>
								<td>${ u.office.name }</td>
								<td>${ u.fullName }</td>
								<td>${ u.username }</td>
								<td><button>Editar</button></td>
								<td><button>Deshabilitar</button></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="../include/footer.jsp"></jsp:include>
</body>
</html>