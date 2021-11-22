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

$(document).ready(function(){
    <!-- 문의하기 -->
    $("#button-send").click(function(){
        $('.send_container').show();
        $('.dim').show();
    });

    $('.btn_close').click(function(){
        $('.send_container').hide();
        $('.dim').hide();
    });

    <!-- 구매하기 -->
    $("#button-buy").click(function(){
        $('.buy_container').show();
        $('.dim').show();
    });

    $('.btn_close').click(function(){
        $('.buy_container').hide();
        $('.dim').hide();
    });


});
