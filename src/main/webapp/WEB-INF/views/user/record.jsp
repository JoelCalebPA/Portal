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
			<div class="table-responsive">
				<table class="table">
					<tbody>
						<c:forEach var="doc" items="${ documents }">
							<tr>
								<td><i class="fa fa-file-text" aria-hidden="true"></i> ${ doc.name }</td>
								<td><i class="fa fa-eye yo-icons" aria-hidden="true"></i></td>
								<td><i class="fa fa-download yo-icons" aria-hidden="true"></i></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<jsp:include page="../include/footer.jsp" />
</body>
</html>