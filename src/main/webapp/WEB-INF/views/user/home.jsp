<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
  <jsp:include page="../include/header.jsp"/>
</head>
<body>
  <jsp:include page="../include/menu.jsp"/>
<%--   <h2>Welcom to da home page ma boi ${pageContext.request.userPrincipal.name}</h2> --%>
  <jsp:include page="../include/footer.jsp" />
</body>
</html>