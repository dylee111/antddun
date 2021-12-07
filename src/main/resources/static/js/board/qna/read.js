$(document).ready(function() {
    var qnaNo = $("#qnaNo").val();
    var mno = $("#mno").val();

    var ddun = parseInt($("#ddun").text());
    var buyerDdun = parseInt($("#total_ddun").val);
    var count = 0;


    $('textarea').each(function () {
      this.setAttribute('style', 'height:' + (this.scrollHeight) + 'px;overflow-y:hidden;');
    }).on('textarea', function () {
      this.style.height = 'auto';
      this.style.height = (this.scrollHeight) + 'px';
    });

    // 구매 버튼
    $(".buy_btn").click(function() {
        console.log(ddun);
        console.log(buyerDdun);
        $.ajax({
            url: "/antddun/member/qna/buy/" + qnaNo + "/" + mno,
            type: "GET",
            data: {
                "amount": ddun
            },
            success: function(data) {
                if(data === "구매 성공") {
                    alert("구매완료");
                } else if(data === "뚠 충전 필요"){
                    alert("뚠이 부족합니다.")
                }

            }
        }); // ajax end.
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


 /*--------------------------댓글--------------------------*/

    // 댓글 등록
    $("#btn_reply").click(function() {
        console.log($(".form-control").val());
        var replyData = {
            qnaNo: qnaNo,
            replyText: $(".form-control").val()
        }

        $.ajax({
            url: "/antddun/member/qna/list/replySave",
            method: "POST",
            dataType: "json",
            data: JSON.stringify(replyData),
            contentType: 'application/json; charset=utf-8',
            success: function() {
                alert("댓글 등록 성공");
                self.location.reload();
            }

        }) // ajax end.
    });

//댓글 수정
    $(".reply-modify").click(function() {
        var parent = $(this).parent().parent();
        var replyNo = parent.children(".reply-no").val();
        var replyText = parent.children(".reply-text-box");
        var replier = parent.children(".replier");

        console.log(replyNo);

        count++;

        if(count == 1){
            console.log(count);
            replyText.removeAttr("readonly");
            replyText.css('border','solid 1px gray');
            replyText.css('border-radius','5px');
            replyText.css('cursor','text');

            replyText.focus();

        } else if(count == 2) {

            var replyModify = {
                qnaRno: replyNo,
                replyText: replyText.val()
            }

            $.ajax({
                url: "/antddun/member/qna/list/read/replyModify/"+replyNo,
                method: "post",
                data: JSON.stringify(replyModify),
                contentType: "application/json; charset=utf-8",
                success: function(result) {
                    if(result === "replyModify") {
                        alert("댓글 수정");
                        self.location.reload();
                    }
                }
            })
            count = 0;
        }
    });

// 댓글 삭제
    $(".reply-delete").click(function() {

        if(!confirm("삭제하시겠습니까?")){
          return;
        }

        var parent = $(this).parent().parent();
        var replyNo = parent.children(".reply-no").val();

        $.ajax({
            url: "/antddun/member/qna/list/replyDelete/" + replyNo,
            method: "post",
            success: function(result) {
                if(result === "delete"){
                console.log(replyNo); //잘나옴
                    alert("댓글이 삭제되었습니다.");
                    self.location.reload();
                }
            }
        }) // ajax end.
    }); // reply-modify click event


}); // end.