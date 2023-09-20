<%@ tag trimDirectiveWhitespaces="true" %>
<%@ attribute name="cart" required="true" type="com.es.core.cart.Cart" %>

<button>
    <p>
        <div id ="totalQuantity">Cart: ${cart.totalQuantity} items</div>
        <div id ="totalCost">${cart.totalCost} $</div>
    </p>
</button>
