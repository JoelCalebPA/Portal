<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<div class="menu navbar-collapse offset collapse"
	id="navbarSupportedContent">
	<div class="account">
		<h4 class="name">${pageContext.request.userPrincipal.name}</h4>
		<form id="logoutForm" method="POST" action="${contextPath}/logout">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<a class="logout" onclick="document.forms['logoutForm'].submit()">Cerrar
			Sesión</a>
	</div>
	<ul class="nav navbar-nav">
		<li class="nav-item ${ active == 'home' ? 'active' : '' }"><a
			class="nav-link"
			href="${ contextPath }/user"> <i class="fa fa-home"
				aria-hidden="true"></i>Boleta Actual
		</a></li>
		<li class="nav-item ${ active == 'record' ? 'active' : '' }"><a
			class="nav-link"
			href="${ contextPath }/user/record"> <i class="fa fa-book"
				aria-hidden="true"></i>Historial
		</a></li>
		<li class="nav-item ${ active == 'account' ? 'active' : '' }"><a
			class="nav-link"
			style="border-bottom-style: solid;"
			href="${ contextPath }/user/account"> <i class="fa fa-user"
				aria-hidden="true"></i>Mi Cuenta
		</a></li>
	</ul>
</div>
<div class="responsive-menu">
	<button class="navbar-toggler collapsed" type="button"
		data-toggle="collapse" data-target="#navbarSupportedContent"
		aria-controls="navbarSupportedContent" aria-expanded="false"
		aria-label="Toggle navigation">
		<span class="icon-bar"></span> <span class="icon-bar"></span> <span
			class="icon-bar"></span>
	</button>
	<a href="${ contextPath }" class="navbar-brand logo-h"> <img
		src="${ contextPath }/resources/images/logocofo.png" />
	</a>
</div>