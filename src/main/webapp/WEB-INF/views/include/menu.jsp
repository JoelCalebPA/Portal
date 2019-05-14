<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<aside class="menu-sidebar2" style="width:25%">
	<div class="logo">
		<a href="#"> <img
			src="${ contextPath }/resources/images/logocofo.png" alt="Cool Admin" />
		</a>
	</div>
	<div class="menu-sidebar2__content">
		<div class="account2">
			<div class="image img-cir img-120">
				<img src="${ contextPath }/resources/images/logocofo.png"
					alt="John Doe" />
			</div>
			<h4 class="name">${pageContext.request.userPrincipal.name}</h4>
			<a href="#">Cerrar Sesión</a>
		</div>
		<nav class="navbar-sidebar2">
			<ul class="list-unstyled navbar__list">
				<li><a href="${ contextPath }/user"> Boleta Actual </a></li>
				<li><a href="${ contextPath }/user/record">Historial </a></li>
				<li><a href="${ contextPath }/user/account">Mi Cuenta </a></li>
			</ul>
		</nav>
	</div>
</aside>



<!-- <div id="navbar" class="d-flex align-content-stretch flex-wrap"> -->
<!-- 	<div id="menu-general" class="p-2"> -->
<!-- 		<div id="usuario-nombre"> -->
<%-- 			<h5>${ pageContext.request.userPrincipal.name }</h5> --%>
<!-- 		</div> -->
<!-- 		<div id="menu-opciones"> -->
<!-- 			<nav class="nav flex-column"> -->
<!-- 				<ul> -->
<!-- 					<li><div> -->
<!-- 							<a id="opc-act" class="nav-link active" -->
<%-- 								href="${ contextPath }/user"> Boleta actual</a> --%>
<!-- 						</div></li> -->
<!-- 					<li><div> -->
<%-- 							<a id="opc" class="nav-link" href="${ contextPath }/user/record">Historial</a> --%>
<!-- 						</div></li> -->
<!-- 					<li><div> -->
<%-- 							<a id="opc" class="nav-link" href="${ contextPath }/user/account">Mi --%>
<!-- 								cuenta</a> -->
<!-- 						</div></li> -->
<%-- 					<li><c:if --%>
<%-- 							test="${pageContext.request.userPrincipal.name != null}"> --%>
<!-- 							<form id="logoutForm" method="POST" -->
<%-- 								action="${contextPath}/logout"> --%>
<%-- 								<input type="hidden" name="${_csrf.parameterName}" --%>
<%-- 									value="${_csrf.token}" /> --%>
<!-- 							</form> -->
<!-- 							<a onclick="document.forms['logoutForm'].submit()">Cerrar -->
<!-- 								sesión</a> -->
<%-- 						</c:if></li> --%>
<!-- 				</ul> -->
<!-- 			</nav> -->
<!-- 		</div> -->
<!-- 	</div> -->
<!-- </div> -->