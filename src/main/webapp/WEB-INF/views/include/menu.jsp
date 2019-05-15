<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<aside class="menu">
	<div class="menu-logo">
		<a href="${ contextPath }"> <img
			src="${ contextPath }/resources/images/logocofo.png" alt="Cool Admin" />
		</a>
	</div>
	<div class="">
		<div class="account">
			<h4 class="name">${pageContext.request.userPrincipal.name}</h4>
			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
			<a class="logout" onclick="document.forms['logoutForm'].submit()">Cerrar
				Sesión</a>
		</div>
		<nav class="menu-navbar">
			<a class="${ active == 'home' ? 'active' : '' }"
				href="${ contextPath }/user"> Boleta Actual </a> <a
				class="${ active == 'record' ? 'active' : '' }"
				href="${ contextPath }/user/record">Historial </a> <a
				class="${ active == 'account' ? 'active' : '' }"
				style="border-bottom-style: solid;"
				href="${ contextPath }/user/account">Mi Cuenta </a>
		</nav>
	</div>
</aside>
<div class="aesthetic-div">
	<div class="menu-mobile"></div>
	<h2>Portal de Boletas</h2>
</div>