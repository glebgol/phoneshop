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
<c:choose>
  <c:when test="${cart.items.size() ne 0}">
    <div style="color: green" id="successMessage">${successMessage}</div>

    <c:if test="${not empty errors}">
      <div style="color: red" id="errorMessage">Errors while updating cart</div>
    </c:if>
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
      <spring:form method="post" id="updateCart" modelAttribute="cartDTO"
                   action="${pageContext.request.contextPath}/cart">
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
                <form:input value="${items[index].quantity}" path="items[${index}].quantity"/>
                <spring:hidden value="${cartItem.phone.id}" path="items[${index}].phoneId"/>
                <c:set var="fieldName" value="items[${index}].quantity"/>
                <div style="color: red" id="errorMessage-${cartItem.phone.id}">${errors[fieldName]}</div>
              </div>
            </td>
            <td>
              <input form="deleteCartItem"
                     formaction="${pageContext.servletContext.contextPath}/cart/delete/${cartItem.phone.id}"
                     type="submit" value="Delete"/>
            </td>
          </tr>
        </c:forEach>
      </spring:form>
      <form id="deleteCartItem" method="post">
      </form>
    </table>
    <p>
      <input form="updateCart" type="submit" value="Update"/>
    </p>
  </c:when>
  <c:otherwise>
    Empty cart
  </c:otherwise>
</c:choose>
