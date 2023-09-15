function handleButtonClick(id, quantity) {
    $.ajax({
        type: "POST",
        url: "http://localhost:8080/phoneshop-web/ajaxCart?phoneId=" + id + "&quantity=" + quantity,
        headers: {
            'Content-Type': 'application/json'
        }
    });
}
