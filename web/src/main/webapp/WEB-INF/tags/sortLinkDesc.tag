<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="sort" required="true" %>
<%@ attribute name="order" required="true" %>
<%@ attribute name="currentPage" required="true" %>

<a href="?page=${currentPage}&sort=${sort}&order=${order}&search=${param.search}">
    <c:choose>
        <c:when test="${sort eq param.sort && order eq param.order}">
            <span class="glyphicon glyphicon-arrow-down" style="color:darkred"></span>
        </c:when>
        <c:otherwise>
            <span class="glyphicon glyphicon-arrow-down"></span>
        </c:otherwise>
    </c:choose>
</a>
