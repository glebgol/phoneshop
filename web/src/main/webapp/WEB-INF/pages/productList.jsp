<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<tags:head title="Product List"/>
<header class="d-flex justify-content-between">
  <tags:cart cart="${cart}"/>
  <form>
    <input class="form-control" aria-label="Small" aria-describedby="inputGroup-sizing-sm" name="search" value="${param.query}">
    <button class="btn">Search</button>
  </form>
</header>
<table class="table table-bordered">
  <thead>
  <tr class="table-secondary">
    <td>Image</td>
    <td>
      <div class="d-flex">
        Brand
        <div>
          <tags:sortLinkAsc sort="BRAND" order="ASC"/>
          <tags:sortLinkDesc sort="BRAND" order="DESC"/>
        </div>
      </div>
    </td>
    <td>
      <div class="d-flex">
        Model
        <div>
          <tags:sortLinkAsc sort="MODEL" order="ASC"/>
          <tags:sortLinkDesc sort="MODEL" order="DESC"/>
        </div>
      </div>
    </td>
    <td>Color</td>
    <td>
      <div class="d-flex">Display size
        <div>
          <tags:sortLinkAsc sort="DISPLAY_SIZE" order="ASC"/>
          <tags:sortLinkDesc sort="DISPLAY_SIZE" order="DESC"/>
        </div>
      </div>
    </td>
    <td>
      <div class="d-flex">Price
        <div>
          <tags:sortLinkAsc sort="PRICE" order="ASC"/>
          <tags:sortLinkDesc sort="PRICE" order="DESC"/>
        </div>
      </div>
    </td>
    <td>Quantity</td>
    <td>Action</td>
  </tr>
  </thead>
  <c:forEach var="phone" items="${phones}"><tr>
    <td>
      <a href="${pageContext.request.contextPath}/productDetails/${phone.id}">
        <img height="90" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}">
      </a>
    </td>
    <td>${phone.brand}</td>
    <td>
      <a href="${pageContext.request.contextPath}/productDetails/${phone.id}">
          ${phone.model}
      </a>
    </td>
    <td>
      <c:forEach var="color" items="${phone.colors}">
        <p>${color.code}</p>
      </c:forEach>
    </td>
    <td>${phone.displaySizeInches}''</td>
    <td><fmt:formatNumber value="${phone.price}" type="currency"
                          currencySymbol="$"/></td>
    <td>
      <div id="addToCart${phone.id}">
        <c:set var="quantity" value="${1}"/>
        <input id="quantity-${phone.id}" class="form-control" name="quantity" value="${quantity}">
        <input name="productId" value="${phone.id}" type="hidden"/>
        <div style="color: green" id="successMessage-${phone.id}"></div>
        <div style="color: red" id="errorMessage-${phone.id}"></div>
      </div>
    </td>
    <td>
      <input type="button" onclick="addToCart(${phone.id})" value="Add to cart">
    </td>
  </tr>
  </c:forEach>
</table>
<tags:pagination pagesCount="${pagesCount}"/>
