$(document).ready(function() {
    var sosoNo = $("#soso_no").val();
    var ddun = parseInt($("#ddun").text());

    $("#button_buy").click(function() {
        console.log(ddun);
        $.ajax({
            url: "/antddun/member/sosojob/buy/" + sosoNo,
            type: "GET",
            data: {
                "amount": ddun
            },
            success: function(data) {
                alert(data);
                alert("구매완료");

            }
        }); // ajax end.
    });


}); // end.