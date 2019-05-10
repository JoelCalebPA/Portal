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

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
	<jsp:include page="include/header.jsp"></jsp:include>
		<div id="body" class="d-flex align-content-stretch flex-wrap-reverse">
			<div id="contentido-sesion" class="p-2">
				<div id="sesion" class="container">

					<form method="POST" action="${contextPath}/login"
						class="form-signin">
						<div id="titulo-sesion" align="center">
							<h5 id="lg">Iniciar Sesión</h5>
						</div>
						<div class="form-group ${error != null ? 'has-error' : ''}">
							<div class="form-group" id="lg">
								<label for="exampleInputEmail1">Usuario</label> <input
									name="username" type="text" class="form-control"
									placeholder="Por ejemplo: user@cofopri.com.pe" autofocus="true" />
								<span>${message}</span>
							</div>
							<div class="form-group" id="lg">
								<label for="exampleInputPassword1">Contraseña</label> <input
									name="password" type="password" class="form-control"
									placeholder="Por ejemplo:70876623" /> <span>${error}</span>
							</div>
							<div class="form-group" id="lg">
								<label for="exampleFormControlSelect1">Sede</label> <select
									class="form-control" id="exampleFormControlSelect1">
									<option>Lima</option>
									<option>Provincia</option>
								</select>
							</div>
							<input type="hidden" name="${_csrf.parameterName}"
								value="${_csrf.token}" />
							<button class="btn btn-lg btn-primary btn-block" type="submit">Iniciar
								sesión</button>
						</div>
					</form>

				</div>
			</div>
		</div>
		<div id="encabezado-sesion" class="p-2">
			<img width="65%" src="${ contextPath }/resources/images/logocofo.png">
			<h5 align="center" id="title">Portal de Boletas de Pago</h5>
		</div>
	</div>

	</div>
	<!-- /container -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>
</body>
</html>
