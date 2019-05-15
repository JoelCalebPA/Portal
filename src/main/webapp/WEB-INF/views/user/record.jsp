<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/header.jsp" />
</head>
<body>
	<jsp:include page="../include/menu.jsp" />
	<div class="page-container">
		<div class="record-filter">
			<form class="form-inline">
				<div class="form-group mb-2">
					<label>Filtros</label>
				</div>
				<div class="form-group mx-sm-3 mb-2">
					<select class="form-control">
						<option>Todos</option>
					</select>
				</div>
				<div class="form-group mx-sm-3 mb-2">
					<select class="form-control">
						<option>2019</option>
					</select>
				</div>
				<button type="submit" class="form-group btn btn-primary mb-2"
					style="background-color: #ffdd00; color: #2d4191;">Buscar</button>
			</form>
		</div>
		<div class="record-results">
			<p>Se han encontrado ${ num } documentos.</p>
			<div class="container-fluid">
				<c:forEach var="doc" items="${ documents }">
					<div class="row">
						<div class="col">s
							<i class="fas fa-file-pdf"></i>
						</div>
						<div class="col">${ doc.name }</div>
						<div class="col">
							<a href="${ contextPath }/Download?node=${doc.uuid}">
								Descargar </a>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
		<jsp:include page="../include/footer.jsp" />
</body>
</html>