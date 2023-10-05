function updateOrder(id, status) {
    const updateOrder = {
        orderStatus: status
    };

    $.ajax({
            headers: {
                'Content-Type': 'application/json'
            },
            type: "PATCH",
            url: contextPath + "/admin/orders/" + id,
            data: JSON.stringify(updateOrder),
            success: function (response) {
                $("#order-status").text(response);
                $("#status-changing-info").text("Status changed to " + status);
            }
        }
    )
}
