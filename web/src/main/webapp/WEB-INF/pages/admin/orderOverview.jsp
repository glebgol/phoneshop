<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<tags:master pageTitle="Cart">
  <hr>
  <div>
    <h3 id="status-changing-info" style="color: green"></h3>
  </div>
  <div class="row">
    <div class="col-5"><h3>Order number:${order.id}</h3></div>
    <div class="col-5"><h3 id="order-status">Order status: ${order.status}</h3>
    </div>
  </div>
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

  <table class="table table-bordered">
    <tr>
      <td>First Name</td>
      <td>${order.firstName}</td>
    </tr>
    <tr>
      <td>Last Name</td>
      <td>${order.lastName}</td>
    </tr>
    <tr>
      <td>Address</td>
      <td>${order.deliveryAddress}</td>
    </tr>
    <tr>
      <td>Phone</td>
      <td>${order.contactPhoneNo}</td>
    </tr>
  </table>
  <p>${order.additionalInfo}</p>
  <a class="btn btn-primary" href="${pageContext.request.contextPath}/admin/orders">Back</a>
  <button class="btn btn-primary" onclick="updateOrder(${order.id}, 'DELIVERED')">Delivered</button>
  <button class="btn btn-primary" onclick="updateOrder(${order.id}, 'REJECTED')">Rejected</button>
</tags:master>
