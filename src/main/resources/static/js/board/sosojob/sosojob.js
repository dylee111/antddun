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