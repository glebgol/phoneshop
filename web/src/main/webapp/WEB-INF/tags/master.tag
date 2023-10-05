<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
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
                <a href="${pageContext.request.contextPath}/admin/orders" class="btn btn-primary">Admin</a>
            </div>
            <div class="col-5">
                <a href="${pageContext.request.contextPath}" class="btn btn-primary">Logout</a>
            </div>
        </div>
    </div>
</header>
<main>
    <jsp:doBody/>
</main>
</body>
</html>
