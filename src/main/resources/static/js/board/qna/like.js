
//function toggleLike(postId) {
//    let likeIcon = $("#storyLikeIcon");
//
//    if (likeIcon.hasClass("far")) { // 좋아요 하겠다
//        $.ajax({
//            type: "post",
//            url: `/api/post/${postId}/likes`,
//            dataType: "text"
//        }).done(res=>{
//            let likeCountStr = $("#storyLikeIcon").text();
//            let likeCount = Number(likeCountStr) + 1;
//            $("#storyLikeIcon").text(likeCount);
//
//            likeIcon.addClass("fas");
//            likeIcon.addClass("active");
//            likeIcon.removeClass("far");
//        }).fail(error=>{
//            console.log("오류", error);
//        });
//    } else { // 좋아요취소 하겠다
//        $.ajax({
//            type: "delete",
//            url: `/api/post/${postId}/likes`,
//            dataType: "text"
//        }).done(res=>{
//            let likeCountStr = $("#storyLikeIcon").text();
//            let likeCount = Number(likeCountStr) - 1;
//            $("#storyLikeIcon").text(likeCount);
//
//            likeIcon.removeClass("fas");
//            likeIcon.removeClass("active");
//            likeIcon.addClass("far");
//        }).fail(error=>{
//            console.log("오류", error);
//        });
//    }
//}
  $(document).ready(function() {
   var contextPath = $('#contextPathHolder').attr('data-contextPath') ? $('#contextPathHolder').attr('data-contextPath') : '';


        $('#likebtn').click(function(){
                likeupdate();
            });

        function likeupdate(){

            var qnaNo = $('#qnaNo').text();
            var likesCnt = $('#likesCnt');

        $.ajax({
            url : contextPath + "/qna/" + qnaNo,
            type : 'POST',
            data: qnaNo,
            dataType: 'JSON',
            success : function(data){
                        if ( data.response == null) { //좋아요 해제 후 db에 있는 개수 반영
                            //likesCnt.html(JSON.stringify(data.likesCnt));
                            likesCnt.text(JSON.stringify(data.likesCnt));

                        } else { //좋아요, db에 있는 개수 반영
                           // likesCnt.html(JSON.stringify(data.likesCnt));
                            likesCnt.text(JSON.stringify(data.likesCnt));
                            console.log("좋아요 개수"+JSON.stringify(data.likesCnt));
                            $('#likebtn').attr('class','liked');
                        }
            },
            error : function(data){
                alert("로그인 후 이용가능");
            }

            });
        };
   });

/*

    success : function(data){
             if (data == "success"){
                console.log("좋아요" + data);
                alert("좋아요");
                $('#likebtn').attr('class','btn-light');
             }else if(data == "fail"){
                console.log("좋아요 취소" + data);
                alert("좋아요 취소");
                $('#likebtn').attr('class','btn-danger');
             }

*/

