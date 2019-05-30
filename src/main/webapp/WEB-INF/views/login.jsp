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
					<div class="acc-img">
						<img src="${ contextPath }/resources/images/login blanco.png">
						<p>${ role.name }</p>
					</div>
					<div class="form-group">
						<label for="username">Usuario</label> <input type="text"
							class="form-control" id="username" name="username"
							placeholder="Por ejemplo: user@cofopri.com.pe"
							autofocus="autofocus"> <span
							class="${error != null ? 'has-error' : ''}">${message}</span>
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
		<div class="right-logo">
			<img width="100%"
				src="${ contextPath }/resources/images/logocofo.png">
			<h5 align="center" id="title">Portal de Boletas de Pago</h5>
			<%-- 			<c:forEach var="rol" items="${ roles }"> --%>
			<%-- 				<p>${ rol }</p> --%>
			<%-- 			</c:forEach> --%>
			<p>${ rol }</p>
		</div>
		<div class="right-footer">
			<div class="fo-1">
				<span>Av. Paseo de la República 3135 - 3137,San Isidro, Lima,
					Perú </span><br> <span>Horario de atención: L-V de 8:30 a.m. a
					4:30 p.m. </span><br> <span>Central telefónica: 319-3838 </span>
			</div>
			<div class="fo-2">
				<img width="100%"
					src="${ contextPath }/resources/images/logo_mvcs.jpg"
					style="width: auto;">
			</div>
		</div>
	</div>
	<jsp:include page="include/footer.jsp" />
</body>
</html>
