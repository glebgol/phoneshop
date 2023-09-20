<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageCount" required="true" %>
<%@ attribute name="currentPage" required="true" %>

<a href="${pageContext.request.contextPath}/productList?page=${pageCount}&search=${param.search}">
    <c:choose>
        <c:when test="${currentPage eq pageCount}">
            <span class="fst-italic font-weight-bold">${pageCount}</span>
        </c:when>
        <c:otherwise>
            ${pageCount}
        </c:otherwise>
    </c:choose>
</a>
