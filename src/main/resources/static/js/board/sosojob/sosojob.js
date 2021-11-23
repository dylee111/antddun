$(document).ready(function() {
    var sosoNo = $("#soso_no").val();
    var mno = $("#writer_no").val();
    var ddun = parseInt($("#ddun").text());
    var buyerDdun = parseInt($("#total_ddun").val);

    // 구매 버튼
    $(".buy_btn").click(function() {
        console.log(ddun);
        console.log(buyerDdun);
        $.ajax({
            url: "/antddun/member/sosojob/buy/" + sosoNo + "/" + mno,
            type: "GET",
            data: {
                "amount": ddun
            },
            success: function(data) {
                if(buyerDdun >= ddun) {
                    alert("구매완료");
                } else if(ddun > buyerDdun){
                    alert("뚠이 부족합니다.")
                }

            }
        }); // ajax end.
    });

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


}); // end.
