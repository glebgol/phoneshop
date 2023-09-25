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
            url: contextPath + "/ajaxCart/",
            dataType: "json",
            data: JSON.stringify(cartItem),
            responseType: 'json',
            success: function (response) {
                $("#totalQuantity").text("Cart: " + response.cartItemsQuantity + " items");
                $("#totalCost").text(response.totalCost + " $");
                $("#errorMessage-" + id).text("");
                $("#successMessage-" + id).text(response.resultMessage);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                try {
                    $("#errorMessage-" + id).text(JSON.parse(jqXHR.responseText).resultMessage);
                } catch {
                    $("#errorMessage-" + id).text("Quantity should be an integer");
                }
                $("#successMessage-" + id).text("");
            }
        }
    )
}
