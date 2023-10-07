<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>

<tags:master pageTitle="Orders">
  <hr>
  <h1>Orders</h1>
  <h3 style="color: red">${errorMessage}</h3>
  <table class="table table-bordered">
    <thead>
    <tr class="table-secondary">
      <td>Order number</td>
      <td>Customer</td>
      <td>Phone</td>
      <td>Address</td>
      <td>Date</td>
      <td>Total price</td>
      <td>Status</td>
    </tr>
    </thead>
    <c:forEach var="order" items="${orders}">
      <tr>
        <td>
          <a href="${pageContext.request.contextPath}/admin/orders/${order.orderNumber}">${order.orderNumber}</a>
        </td>
        <td>${order.firstName} ${order.lastName}</td>
        <td>${order.contactPhoneNo}</td>
        <td>${order.deliveryAddress}</td>
        <td><fmt:formatDate value="${order.date}" pattern="yyyy.MM.dd HH:mm"/></td>
        <td><fmt:formatNumber value="${order.totalPrice}" type="currency" currencySymbol="$"/></td>
        <td>${order.status}</td>
      </tr>
    </c:forEach>
  </table>
</tags:master>
