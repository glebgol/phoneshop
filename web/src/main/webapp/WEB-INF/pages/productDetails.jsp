<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<tags:head title="${phone.model}"/>

<tags:cart cart="${cart}"/>
<hr>
<tags:productListLink/>
<div class="container-fluid">
  <h3>${phone.model}</h3>
  <div class="row">
    <div class="col-7">
      <img height="200" src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}">
      <p>${phone.description}</p>
      <div class="float-left">
        <p>Price: $${phone.price}</p>
        <input id="quantity-${phone.id}" class="form-control" name="quantity" value="1">
        <input name="productId" value="${phone.id}" type="hidden"/>
        <div style="color: green" id="successMessage-${phone.id}"></div>
        <div style="color: red" id="errorMessage-${phone.id}"></div>
        <input type="button" onclick="addToCart(${phone.id})" value="Add to cart">
      </div>
    </div>

    <div class="col-4">
      <h4>Display</h4>
      <table class="table table-bordered">
        <tr>
          <td>Size</td>
          <td>${phone.displaySizeInches}"</td>
        </tr>
        <tr>
          <td>Resolution</td>
          <td>${phone.displayResolution}</td>
        </tr>
        <tr>
          <td>Technology</td>
          <td>${phone.displayTechnology}</td>
        </tr>
        <tr>
          <td>Pixel density</td>
          <td>${phone.pixelDensity}</td>
        </tr>
      </table>
      <h4>Dimensions & weight</h4>
      <table class="table table-bordered">
        <tr>
          <td>Length</td>
          <td>${phone.lengthMm} mm</td>
        </tr>
        <tr>
          <td>Width</td>
          <td>${phone.widthMm} mm</td>
        </tr>
        <tr>
          <td>Weight</td>
          <td>${phone.weightGr} g</td>
        </tr>
      </table>
      <h4>Camera</h4>
      <table class="table table-bordered">
        <tr>
          <td>Front</td>
          <td>${phone.frontCameraMegapixels}</td>
        </tr>
        <tr>
          <td>Back</td>
          <td>${phone.backCameraMegapixels}</td>
        </tr>
      </table>
      <h4>Battery</h4>
      <table class="table table-bordered">
        <tr>
          <td>Talk time</td>
          <td>${phone.talkTimeHours} hours</td>
        </tr>
        <tr>
          <td>Stand by time</td>
          <td>${phone.standByTimeHours} hours</td>
        </tr>
        <tr>
          <td>Battery capacity</td>
          <td>${phone.batteryCapacityMah} mAh</td>
        </tr>
      </table>
      <h4>Other</h4>
      <table class="table table-bordered">
        <tr>
          <td>Colors</td>
          <td>
            <ul>
              <c:forEach var="color" items="${phone.colors}">
                <p>${color.code}</p>
              </c:forEach>
            </ul>
          </td>
        </tr>
        <tr>
          <td>Device type</td>
          <td>${phone.deviceType}</td>
        </tr>
        <tr>
          <td>Bluetooth</td>
          <td>${phone.bluetooth}</td>
        </tr>
      </table>
    </div>
  </div>
</div>
