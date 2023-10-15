<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ attribute name="pageTitle" required="true" %>

<html>
<head>
    <meta charset="UTF-8">
    <tags:links/>
    <title>${pageTitle}</title>
</head>
<body>
<header>
    <div class="container">
        <div class="float-md-right">
            <div class="col-5">
                <security:authorize access="!isAuthenticated()">
                    <a class="btn btn-primary" href="<c:url value="/login"/>">Login </a>
                </security:authorize>
            </div>
            <div class="col-5">
                <security:authorize access="isAuthenticated()">
                    <security:authentication property="principal.username" />
                </security:authorize>
            </div>
            <div class="col-5">
                <security:authorize access="isAuthenticated()">
                    <a href="${pageContext.request.contextPath}/admin/orders" class="btn btn-primary">Admin</a>
                </security:authorize>
            </div>
            <div class="col-5">
                <security:authorize access="isAuthenticated()">
                    <a href="${pageContext.request.contextPath}/logout" class="btn btn-primary">Logout</a>
                </security:authorize>
            </div>
            <div class="col-5">
                <security:authorize access="isAuthenticated()">
                    <a href="${pageContext.request.contextPath}" class="btn btn-primary">Products</a>
                </security:authorize>
            </div>
        </div>
    </div>
</header>
<main>
    <jsp:doBody/>
</main>
</body>
</html>
