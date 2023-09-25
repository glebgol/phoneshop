<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="cart" required="true" type="com.es.core.cart.Cart" %>

<form action="${pageContext.request.contextPath}/cart">
    <button>
        <p>
            <span id ="totalQuantity">Cart: ${cart.totalQuantity} items</span>
        </p>
        <p>
            <span id ="totalCost">${cart.totalCost} $</span>
        </p>
    </button>
</form>
