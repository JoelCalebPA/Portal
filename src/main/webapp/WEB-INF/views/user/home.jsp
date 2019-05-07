<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html lang="en">
<head>
<meta >
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
	<jsp:include page="../include/menu.jsp" />
	
	<h2>Welcom to da home page ma boi
		${pageContext.request.userPrincipal.name}</h2>

	<h3>here is your document: ${ document.path.substring(document.path.lastIndexOf("/")+1) }</h3>
	<h3>here is your preview:</h3>
	<div class="col-lg-6">
		<div class="row">
			<p>
				<span class="glyphicon glyphicon-file" aria-hidden="true"></span> <b>${document.path}</b>
			</p>
		</div>
			<iframe id="preview-doc" src="${previewUrl}" width="100%" height="100%" ></iframe>
	</div>

	<script type="text/javascript">
		window.onresize = calculatePreviewHeight;
	</script>
	<jsp:include page="../include/footer.jsp" />
</body>
</html>