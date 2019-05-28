<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>Log in with your account</title>
<jsp:include page="include/header.jsp"></jsp:include>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<div class="login-left responsive-left">
		<div class="login-logo">
			<img width="100%"
				src="${ contextPath }/resources/images/logocofo.png">
		</div>
		<div class="form">
			<div class="wrapper">
				<form method="POST" action="${contextPath}/login"
					class="form-signin">
					<div align="center">
						<h5 id="lg">Iniciar Sesión</h5>
					</div>
					<div class="form-group">
						<label for="username">Usuario</label> <input type="text"
							class="form-control" id="username" name="username"
							placeholder="Por ejemplo: user@cofopri.com.pe" autofocus="true">
						<span class="${error != null ? 'has-error' : ''}">${message}</span>
					</div>
					<div class="form-group">
						<label for="password">Contraseña</label> <input name="password"
							type="password" class="form-control"
							placeholder="Por ejemplo:70876623" /> <span
							class="${error != null ? 'has-error' : ''}">${error}</span>
					</div>
					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}" />
					<div>
						<button class="btn btn-lg btn-primary btn-block" type="submit">Iniciar
							sesión</button>
					</div>
				</form>
			</div>
		</div>
		<div class="login-footer">
			<img width="100%"
				src="${ contextPath }/resources/images/logo_mvcs.jpg"
				style="bottom: 0; height: 50px; width: 250px;">
		</div>
	</div>
	<div class="login-right responsive-right">
		<div>
			<img width="100%"
				src="${ contextPath }/resources/images/logocofo.png">
			<h5 align="center" id="title">Portal de Boletas de Pago</h5>
			<%-- 			<c:forEach var="rol" items="${ roles }"> --%>
			<%-- 				<p>${ rol }</p> --%>
			<%-- 			</c:forEach> --%>
			<p>${ rol }</p>
		</div>
		<img width="100%"
			src="${ contextPath }/resources/images/logo_mvcs.jpg"
			style="bottom: 0; height: 50px; width: 250px; margin-top: 60%;">
	</div>
	<jsp:include page="include/footer.jsp" />
</body>
</html>
