<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta>
<jsp:include page="../include/header.jsp" />
<script type="text/javascript">
	function calculatePreviewHeight() {
		$("#preview-doc").height(
				$(window).height() - $("footer").height() - $("nav").height()
						- $(".page-header").height() - 110);
	}

	function getName(path) {
		return path.replace(/^.*[\\\/]/, '');
	}
</script>
</head>
<body>
	<div id="encabezado-general" class="p-2">
		<img width="30%" src="${ contextPath }/resources/images/logocofo.png">
	</div>
	<h2>Welcom to da home page ma boi
		${pageContext.request.userPrincipal.name}</h2>
	<c:forEach var="role" items="${ user.roles }">
		<p>${ role.name }</p>
	</c:forEach>

	<h3>here is your document: ${ currentDoc.name }</h3>
	<h3>here is your preview:</h3>
	<div class="col-lg-6">
		<div class="row">
			<p>
				<span class="glyphicon glyphicon-file" aria-hidden="true"></span> <b>${currentDoc.path}</b>
			</p>
		</div>
		<iframe id="preview-doc" src="${ currentDoc.downloadUrl }"
			width="100%" height="100%"></iframe>
	</div>

	<script type="text/javascript">
		window.onresize = calculatePreviewHeight;
	</script>
	<jsp:include page="../include/footer.jsp" />
</body>
</html>