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
		<div class="record-filter">
			<form class="form-inline">
				<div class="form-group mb-2">
					<label>Tipo de contrato</label>
				</div>
				<div class="form-group mx-sm-3 mb-2">
					<select class="form-control">
						<option>CAS</option>
					</select>
				</div>
				<button type="submit" class="form-group btn btn-primary mb-2"
					style="background-color: #ffdd00; color: #2d4191;">Descargar</button>
			</form>
		</div>
		<c:if test="${ !noDocs }">
			<embed showcontrols="false" style="margin-top: 15px;"
				src="${ contextPath }/Preview?node=${ currentDoc.uuid }"
				type="application/pdf" width="100%" height="350">
		</c:if>
	</div>
	<jsp:include page="../include/footer.jsp" />
</body>
</html>