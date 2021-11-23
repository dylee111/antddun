$(document).ready(function() {
    //Messeage Show/Hide
    $('.send_msg').last().css("display", "block");
    $('.sender_info').on("click", function(){
        if($(this).next().css("display") == "none") {
            $(this).next().css("display", "block");
        } else {
            $(this).next().css("display", "none");
        }
    });

    //거래자와의 쪽지 보이기
    $('#park').click(function(){
        $('#mail_lee').hide();
        $('#mail_park').show();
    });
    $('#lee').click(function(){
        $('#mail_lee').show();
        $('#mail_park').hide();
    });

    //거래중/거래완료 버튼
    $('.button--primary').click(function(){
        if($('.button--primary').css("background", "rgb(255, 214, 51)") == true) {
            $('.button--with-icon').css("color", "lightgray");
            $('.button--primary').css("box-shadow", "0 3px 0 0 lightgray");
        } else {
            $('.button--with-icon').css("color", "white");
            $('.button--primary').css("box-shadow", "0 3px 0 0 #b89300");
            $('.button--primary').css("background", "#ffd633");
            $('.button--primary').val("완료");
        }
    });
});