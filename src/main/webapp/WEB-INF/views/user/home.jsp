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
						<option>...</option>
					</select>
				</div>
				<div class="form-group btn btn-primary mb-2"
					style="background-color: #ffdd00; color: #2d4191;">
					<a href="${ contextPath }/Download?node=${ currentDoc.uuid }">Descargar</a>
				</div>
			</form>
		</div>
		<c:if test="${ !noDocs }">
			<c:if test="${ device.equals('smartphone') }">
				<img alt="" src="${ contextPath }/Preview?node=${ currentDoc.uuid }"
					style="width: 100%; max-width: 794px; max-height: 561px;">
			</c:if>
			<c:if test="${ device.equals('pc') }">
				<embed showcontrols="false"
					style="margin-top: 15px; max-width: 794px; max-height: 561px;"
					src="${ contextPath }/Preview?node=${ currentDoc.uuid }"
					type="application/pdf" width="100%" height="561">
			</c:if>
		</c:if>
	</div>
	<jsp:include page="../include/footer.jsp" />
</body>
</html>