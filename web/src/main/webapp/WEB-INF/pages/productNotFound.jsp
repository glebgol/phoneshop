<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<tags:master pageTitle="Product Not Found">
    <tags:cart cart="${cart}"/>
    <hr>
    <h3>Product not found</h3>
    <hr>
    <tags:productListLink/>
</tags:master>
