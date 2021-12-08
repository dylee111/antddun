$(document).ready(function() {

    var replyMno = 0;
    var sosoNo = 0;

    //Messeage Show/Hide
    $('.send_msg').last().css("display", "block");

    $('.sender_info').last().css("background", "#fafafa");
    $('.sender_info').last().css("border-radius", "5px");
    // ajax 호출 후, click 이벤트 적용이 되지 않아 수정.
    $(document).on("click",'.sender_info', function(){
        var msgNo = $(this).children(".msgNo").val();

        if($(this).next().css("display") == "none") {
            $(this).next().css("display", "block");
            $(this).css("background", "#fafafa");
            $(this).css("border-radius", "5px");
        } else {
            $(this).next().css("display", "none");
            $(this).css("background", "none");
        }
        replyMno = $(this).children(".msgMno").val();
        sosoNo = $(this).children(".sosoNo").val();

        $.ajax({
            url: "/antddun/member/messenger/readCheck/" + msgNo,
            type: "post",
            dataType: "JSON",
            data: {msgNo: msgNo},
            success: function(data) {
                alert("수정 성공");
            }
        }); // ajax
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
    $('.trade_btn').click(function(){
        replyMno = $(this).parent().children(".sender_mno").val();
        sosoNo = $(this).parent().children(".sender_sosoNo").val();
        tradeState = $(this).val();
        console.log("before "+tradeState);
        if(tradeState == "true") { tradeState = "false"; }
        else if(tradeState == "false") { tradeState = "true"; }

        console.log("A " + replyMno);
        console.log("B " + sosoNo);
        console.log("C " + tradeState);

        $.ajax({
            url: '/antddun/member/messenger/changeTrade/' + sosoNo +"/" + replyMno,
            method: 'patch',
            data: {
                tradeState: tradeState,
                sosoNo: sosoNo,
                sendMember: replyMno
            },
            success: function(result) {
                if(result === "tradeChange") {console.log("성공");}
            }

        });

        if(tradeState == "true") {
            $(this).css("background", "lightgray");
            $(this).text('완료');
        } else if(tradeState == "false"){
            $(this).css("background", "rgb(255, 214, 51)");
            $(this).text('거래 중');

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

        return date.getFullYear() + "년 " +
            (date.getMonth()+1 >= 10 ? date.getMonth()+1 : "0"+(date.getMonth()+1)) + "월 " +
            (date.getDate() >= 10 ? date.getDate() : "0"+date.getDate()) + "일 " +
            (date.getHours() >= 10 ? date.getHours() : "0"+date.getHours()) + ":" +
            (date.getMinutes() >= 10 ? date.getMinutes() : "0" + date.getMinutes());
    }

    // JSON Data 호출

    function loadJSONData(mno) {
        $.getJSON('/antddun/member/messenger/'+mno, function(arr) {

            var str = "";

            $.each(arr, function(idx, msg) {
                console.log(msg);
                str += '<div class="mail">' ;
                str += '<div class="sender_info">';
                str += '<input type="hidden" class="msgNo" value="'+msg.msgNo+'">'
                str += '<input type="hidden" class="sosoNo" value="'+msg.board.sosoNo+'">'
                str += '<input type="hidden" class="msgMno" value="'+msg.sendMember.mno+'"/>'
                str += '<div  class="sender_wrap">';
                str += '<span class="send_title2">'+msg.msgTitle+'</span>';
                str += '<div class="send_info">';
                str += '<strong>'+msg.sendMember.lastName + msg.sendMember.firstName+'</strong>';
                str += '<span>(<span>'+msg.sendMember.username+'</span>)</span>';
                str += '<span class="send_time">'+formatTime(msg.sendTime)+'</span>';
                str += '</div>';
                str += '</div>';
                str += '</div>';
                str += '<div class="send_msg">';
                str += '<div class="msg_wrap">';
                str += '<p>'+msg.msgContent+'</p>'
                str += '</div>';
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

        console.log("replyMno"+sosoNo);
        $.ajax({
            url: "/antddun/member/messenger/sendMessage/" + replyMno,
            type: "POST",
            data: {
                msgTitle: title.val(),
                msgContent: content.val(),
                board: sosoNo
            },
            success: function(data) {
                alert("메세지 전송 성공");
                self.location.reload();

            }
        }); // ajax end.
        loadJSONData(replyMno);

    });


}); // document end.