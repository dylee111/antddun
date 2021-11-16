$(document).ready(function() {
    //Messeage Show/Hide
    $('.send_msg').last().css("display", "block");
    $('.sender_info').first().on("click", function(){
        if($('.send_msg').css("display") == "none") {
            $('.send_msg').first().css("display", "block");
        } else {
            $('.send_msg').first().css("display", "none");
        }
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