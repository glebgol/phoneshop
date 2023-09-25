<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<tags:master pageTitle="Cart">
  <h3>Order</h3>
  <hr>
  <p>
    <c:choose>
      <c:when test="${order.orderItems.size() ne 0}">
        <tags:cartLink/>
      </c:when>
      <c:otherwise>
        <tags:productListLink/>
      </c:otherwise>
    </c:choose>
  </p>
  <h3>
      ${message}
  </h3>
  <c:choose>
    <c:when test="${order.orderItems.size() ne 0}">

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
        </tr>
        </thead>
          <c:forEach var="orderItem" items="${order.orderItems}" varStatus="loop">
            <c:set var="index" value="${loop.index}"/>
            <tr>
              <td>
                <a href="${pageContext.request.contextPath}/productDetails/${orderItem.phone.id}">
                  <img height="90" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${orderItem.phone.imageUrl}">
                </a>
              </td>
              <td>${orderItem.phone.brand}</td>
              <td>
                <a href="${pageContext.request.contextPath}/productDetails/${orderItem.phone.id}">
                    ${orderItem.phone.model}
                </a>
              </td>
              <td>
                <c:forEach var="color" items="${orderItem.phone.colors}">
                  <p>${color.code}</p>
                </c:forEach>
              </td>
              <td>${orderItem.phone.displaySizeInches}''</td>
              <td><fmt:formatNumber value="${orderItem.phone.price}" type="currency"
                                    currencySymbol="$"/>
              </td>
              <td>${orderItem.quantity}</td>
            </tr>
          </c:forEach>
        <tr>
          <td></td><td></td><td></td><td></td><td></td>
          <td>Subtotal</td>
          <td><fmt:formatNumber value="${order.subtotal}" type="currency" currencySymbol="$"/></td>
        </tr>
        <tr>
          <td></td><td></td><td></td><td></td><td></td>
          <td>Delivery</td>
          <td><fmt:formatNumber value="${order.deliveryPrice}" type="currency" currencySymbol="$"/></td>
        </tr>
        <tr>
          <td></td><td></td><td></td><td></td><td></td>
          <td>TOTAL</td>
          <td><fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol="$"/></td>
        </tr>
      </table>
      <spring:form method="post" id="placeOrder" modelAttribute="orderForm"
                   action="${pageContext.request.contextPath}/order">
        <table class="table-borderless">
          <tr>
            <td>
              <form:label path="firstName">First Name*</form:label>
            </td>
            <td>
              <form:input value="${order.firstName}" path="firstName"/>
              <div style="color: red">
                ${errors["firstName"]}
              </div>
            </td>
          </tr>
          <tr>
            <td>
              <form:label path="lastName">Last Name*</form:label>
            </td>
            <td>
              <form:input value="${order.lastName}" path="lastName"/>
              <div style="color: red">
                  ${errors["lastName"]}
              </div>
            </td>
          </tr>
          <tr>
            <td>
              <form:label path="deliveryAddress">Address*</form:label>
            </td>
            <td>
              <form:input value="${order.deliveryAddress}" path="deliveryAddress"/>
              <div style="color: red">
                  ${errors["deliveryAddress"]}
              </div>
            </td>
          </tr>
          <tr>
            <td>
              <form:label path="contactPhoneNo">Phone*</form:label>
            </td>
            <td>
              <form:input value="${order.contactPhoneNo}" path="contactPhoneNo"/>
              <div style="color: red">
                  ${errors["contactPhoneNo"]}
              </div>
            </td>
          </tr>
        </table>
        <br>
        <form:textarea value="${order.additionalInfo}" path="additionalInfo" placeHolder="Additional information"/>
      </spring:form>
      <input class="btn btn-primary" form="placeOrder" type="submit" value="Order"/>
    </c:when>
    <c:otherwise>
      <h3>Empty cart</h3>
    </c:otherwise>
  </c:choose>
</tags:master>
