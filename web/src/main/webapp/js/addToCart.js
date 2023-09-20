function addToCart(id) {
    const cartItem = {
        phoneId: id,
        quantity: $("#quantity-" + id).val()
    };
    $.ajax({
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            type: "POST",
            url: "ajaxCart/",
            dataType: "json",
            data: JSON.stringify(cartItem),

            success: function (response) {
                $("#totalQuantity").text("Cart: " + response.cartItemsQuantity + " items");
                $("#totalCost").text(response.totalCost + " $");
                $("#errorMessage-" + id).text("");
                $("#successMessage-" + id).text(response.resultMessage);
            },
            error: function () {
                $("#errorMessage-" + id).text("Invalid quantity");
                $("#successMessage-" + id).text("");
            }
        }
    )
}
