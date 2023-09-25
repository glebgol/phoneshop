<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="currentPage" scope="request" type="java.lang.Integer"/>
<%@ attribute name="pagesCount" required="true" %>

<div>
    <c:if test="${pagesCount ne 0}">
        <c:if test="${currentPage gt 1}">
            <a href="${pageContext.request.contextPath}/productList?page=${currentPage - 1}&search=${param.search}">
                <span class="glyphicon glyphicon-arrow-left"></span>
            </a>
        </c:if>

        <c:choose>
            <c:when test="${currentPage eq 1}">
                <c:forEach var="i" begin="0" end="9">
                    <c:if test="${currentPage + i le pagesCount}">
                        <tags:pageElement currentPage="${currentPage}" pageCount="${currentPage+i}"/>
                    </c:if>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <c:forEach var="i" begin="0" end="${pagesCount le 9 ? pagesCount : 9}">
                    <c:if test="${currentPage + i - 1 le pagesCount}">
                        <tags:pageElement currentPage="${currentPage}" pageCount="${currentPage + i - 1}"/>
                    </c:if>
                </c:forEach>
            </c:otherwise>
        </c:choose>

        <c:if test="${currentPage lt pagesCount}">
            <a href="${pageContext.request.contextPath}/productList?page=${currentPage + 1}&search=${param.search}">
                <span class="glyphicon glyphicon-arrow-right"></span>
            </a>
        </c:if>
    </c:if>
</div>
