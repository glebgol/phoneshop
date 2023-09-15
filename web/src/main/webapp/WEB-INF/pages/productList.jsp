<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
      integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.1/css/all.min.css"/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
  <title></title>
</head>
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
          <tags:sortLinkAsc currentPage="${currentPage}" sort="BRAND" order="ASC"/>
          <tags:sortLinkDesc currentPage="${currentPage}" sort="BRAND" order="DESC"/>
        </div>
      </div>
    </td>
    <td>
      <div class="d-flex">
        Model
        <div>
          <tags:sortLinkAsc currentPage="${currentPage}" sort="MODEL" order="ASC"/>
          <tags:sortLinkDesc currentPage="${currentPage}" sort="MODEL" order="DESC"/>
        </div>
      </div>
    </td>
    <td>Color</td>
    <td>
      <div class="d-flex">Display size
        <div>
          <tags:sortLinkAsc currentPage="${currentPage}" sort="DISPLAY_SIZE" order="ASC"/>
          <tags:sortLinkDesc currentPage="${currentPage}" sort="DISPLAY_SIZE" order="DESC"/>
        </div>
      </div>
    </td>
    <td>
      <div class="d-flex">Price
        <div>
          <tags:sortLinkAsc currentPage="${currentPage}" sort="PRICE" order="ASC"/>
          <tags:sortLinkDesc currentPage="${currentPage}" sort="PRICE" order="DESC"/>
        </div>
      </div>
    </td>
    <td>Quantity</td>
    <td>Action</td>
  </tr>
  </thead>
  <c:forEach var="phone" items="${phones}"><tr>
    <td>
      <img height="90" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}">
    </td>
    <td>${phone.brand}</td>
    <td>${phone.model}</td>
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
        <input class="form-control" name="quantity" value="${quantity}">
        <input name="productId" value="${phone.id}" type="hidden"/>
        <p class="error" id="error${phone.id}"></p>
      </div>
    </td>
    <td>
      <button class="btn btn-light" onclick="handleButtonClick(${phone.id}, ${quantity})">Add to cart</button>
    </td>
  </tr>
  </c:forEach>
</table>
<tags:pagination pagesCount="${pagesCount}"/>

<script src="scripts/addToCart.js" type="text/javascript"></script>