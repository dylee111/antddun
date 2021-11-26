$(document).ready(function() {
    var jayuNo = $("#jayu_no").val();

    // 댓글 등록
    $(".btn-reply").click(function() {
        var replyData = {
            jayuNo: jayuNo,
            text: $(".form-control").val()
        }

        $.ajax({
            url: '/antddun/reply/register',
            method: 'POST',
            dataType: 'json',
            data: JSON.stringify(replyData),
            contentType: 'application/json; charset=utf-8',
            success: function() {
                alert("댓글 등록 성공");
                self.location.reload();
            }
        }) // ajax end.
    });

 //리뷰 리스트 보기
//function getReply() {
//    function formatTime(str) {
//        var date = new Date(str);
//        return date.getFullYear()+'/'+
//        (date.getMonth() + 1)+'/' +
//        date.getDate() + ' ' +
//        date.getHours()+':'+
//        date.getMinutes();
//    }
//
//    $.getJSON('antddun/reply/list', function(arr) {
//    var str = "";
//
//        $.each(arr, function(idx, reply) {
//            str += ' <b class="reviewNum">'+review.reviewNum+'</b>';
//            str += ' <h5 class="reviewText">'+review.text+'</h5>';
//            str += ' <p>'+formatTime(review.regDate)+'</p>';
//            str += ' </div>';
//        console.log("review>>>>>>>>>>>"+review);
//        });
//
//        $(".rv-list-table").html(str);
//        //console.log(">>>>>>>>>>>"+str);
//    });
//}
//getReply();


}); // end.
