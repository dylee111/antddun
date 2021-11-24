$(document).ready(function() {
    var replyMno = 0;
    //Messeage Show/Hide
    $('.send_msg').last().css("display", "block");
    // ajax 호출 후, click 이벤트 적용이 되지 않아 수정.
    $(document).on("click",'.sender_info', function(){

        if($(this).next().css("display") == "none") {
            $(this).next().css("display", "block");
        } else {
            $(this).next().css("display", "none");
        }
        replyMno = $(this).children(".msgMno").val();
        console.log("보내기"+replyMno);
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

        if($('.button--primary').css("background", "#ffd633") == true) {

        }
    });

    // 거래자 별 메세지 리스트 호출
    var listGroup = $("#mail-box");

    $(".msg_sender").click(function() {
        var senderMno = $(this).children(".sender_mno").val();
        replyMno = senderMno;
        loadJSONData(senderMno);
    }); // click event end.

    function formatTime(time) {
        var date = new Date(time);

        return date.getFullYear() + "년" +
            (date.getMonth()+1) + "월" +
            date.getDate() + "일" +
            date.getHours() + ":" +
            date.getMinutes();
    }


    function loadJSONData(mno) {
        $.getJSON('/antddun/member/messenger/'+mno, function(arr) {

            var str = "";

            $.each(arr, function(idx, msg) {

                str += '<div class="mail">' ;
                str += '<div class="sender_info">';
                str += '<input type="hidden" class="msgMno" value="'+msg.sendMember.mno+'"/>'
                str += '<div style="height: 45px;">';
                str += '<strong class="sender">';
                str += '<div>'+msg.sendMember.lastName + msg.sendMember.firstName+'</div> </strong>';
                str += '<span>(<span>'+msg.sendMember.username+'</span>)</span>';
                str += '<span class="send_time">'+formatTime(msg.sendTime)+'</span>';
                str += '</div>';
                str += '</div>';
                str += '<div class="send_msg">';
                str += '<p>'+msg.msgContent+'</p>'
                str += '</div>';
                str += '</div>';
                str += '</div>';
            })
            listGroup.html(str);
            $('.send_msg').last().css("display", "block");

        });

    } // loadJSONData() end.

    // 답장 보내기
    $(document).on('click','.reply_btn', function() {
        var title = $("#send_title");
        var content = $("#summernote");
        console.log("replyMno"+replyMno);
        $.ajax({
            url: "/antddun/member/messenger/sendMessage/" + replyMno,
            type: "POST",
            data: {
                msgTitle: title.val(),
                msgContent: content.val()
            },
            success: function(data) {
                alert("메세지 전송 성공");
                self.location.reload();

            }
        }); // ajax end.
        loadJSONData(replyMno);

    });
    $('input:radio[name="trade"]').click(function() {
        var tradeCheck = $('input:radio[name="trade"]:checked').val();

        $.ajax({
            url: "/antddun/member/messenger/tradeCheck",
            dataType: 'text',
            data: {
                trade: tradeCheck
            },
            success: function(data) {
                if(data === "tradeChange") {
                    alert("거래 변경 OK");
                }
            }
        });
    });

}); // document end.