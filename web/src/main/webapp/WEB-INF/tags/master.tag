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
<main>
    <jsp:doBody/>
</main>
</body>
</html>
