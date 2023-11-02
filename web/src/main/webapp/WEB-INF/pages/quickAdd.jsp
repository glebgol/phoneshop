<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<tags:master pageTitle="Quick Add">
  <tags:cart cart="${cart}"/>
  <p>
    <tags:productListLink/>
  </p>
  <h2 style="color: red">${errorMessage}</h2>

  <c:forEach var="item" items="${success}">
    <h3 style="color: green">${item} added successfully</h3>
  </c:forEach>
  <table class="table table-bordered">
  <thead>
  <tr class="table-secondary">
    <td>Product model</td>
    <td>
      <div>
        Quantity
      </div>
    </td>
  </tr>
  </thead>
  <tbody>
  <spring:form method="post" modelAttribute="quickAddDto" id="quickAdd"
               action="${pageContext.request.contextPath}/quickAdd">
    <c:forEach begin="0" end="${quickAddItemsCount - 1}" varStatus="loop">
      <c:set var="index" value="${loop.index}"/>
      <tr>
        <td>
          <form:input value="${quickAddDto.items.get(index).model}" name="Model" path="items[${index}].model"/>
          <c:set var="fieldName" value="items[${index}].model"/>
          <div style="color: red">${errors[fieldName]}</div>
          <form:errors style="color: red" path="items[${index}].model"/>
        </td>
        <td>
          <form:input value="${quickAddDto.items.get(index).quantity}" name="Quantity" path="items[${index}].quantity"/>
          <c:set var="fieldName" value="items[${index}].quantity"/>
          <div style="color: red">${errors[fieldName]}</div>
          <form:errors style="color: red" path="items[${index}].quantity"/>

        </td>
        </td>
      </tr>
    </c:forEach>
  </spring:form>
  </tbody>
  </table>
  <input class="btn btn-primary" type="submit" form="quickAdd" value="Quick Add"/>
</tags:master>
