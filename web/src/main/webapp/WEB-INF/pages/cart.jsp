<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<tags:head title="Cart"/>
<br>
<tags:productListLink/>
<br>
<table class="table table-bordered">
  <thead>
  <tr class="table-secondary">
    <td>Image</td>
    <td>
      <div class="d-flex">
        Brand
      </div>
    </td>
    <td>
      <div class="d-flex">
        Model
      </div>
    </td>
    <td>Color</td>
    <td>
      <div class="d-flex">Display size
      </div>
    </td>
    <td>
      <div class="d-flex">Price
      </div>
    </td>
    <td>Quantity</td>
    <td>Action</td>
  </tr>
  </thead>
<%--  <form:form method="post" modelAttribute="cartItemListDTO"--%>
<%--             action="${pageContext.request.contextPath}/cart">--%>
    <c:forEach var="cartItem" items="${cart.items}" varStatus="loop">
      <c:set var="index" value="${loop.index}"/>
      <tr>
      <td>
        <a href="${pageContext.request.contextPath}/productDetails/${cartItem.phone.id}">
          <img height="90" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${cartItem.phone.imageUrl}">
        </a>
      </td>
      <td>${cartItem.phone.brand}</td>
      <td>
        <a href="${pageContext.request.contextPath}/productDetails/${cartItem.phone.id}">
            ${cartItem.phone.model}
        </a>
      </td>
      <td>
        <c:forEach var="color" items="${cartItem.phone.colors}">
          <p>${color.code}</p>
        </c:forEach>
      </td>
      <td>${cartItem.phone.displaySizeInches}''</td>
      <td><fmt:formatNumber value="${cartItem.phone.price}" type="currency"
                            currencySymbol="$"/></td>
      <td>
        <div>
<%--          <form:input path="items[${index}].quantity"/>--%>
<%--          <form:hidden path="items[${index}].phoneId"/>--%>
          <div style="color: green" id="successMessage-${cartItem.phone.id}"></div>
          <div style="color: red" id="errorMessage-${cartItem.phone.id}"></div>
        </div>
      </td>
      <td>
        <form method="post" id="deleteCartItem" action="${pageContext.request.contextPath}/cart/${cartItem.phone.id}">
          <input type="submit" value="Delete"/>
        </form>
      </td>
    </tr>
    </c:forEach>
<%--  </form:form>--%>

</table>
