
  $(document).ready(function() {
    var contextPath = $('#contextPathHolder').attr('data-contextPath') ? $('#contextPathHolder').attr('data-contextPath') : '';
    var qnaNo = $('#qnaNo').text();
    var likesCnt = $('#likesCnt');
    var likesBtn = $('#likeBtn');

    $('#likeBtn').click(function(){
            likeupdate();
        });

        function likeupdate(){
        $.ajax({
            url : contextPath + "/qna/" + qnaNo,
            type : 'POST',
            data: qnaNo,
            dataType: 'JSON',
            success : function(data){
                        if ( data.response == null) { //좋아요 해제 후 db에 있는 개수 반영
                            likesCnt.text(JSON.stringify(data.likesCnt));
                            likesBtn.addClass("like_on");


                        } else { //좋아요, db에 있는 개수 반영
                            likesCnt.text(JSON.stringify(data.likesCnt));

                        }
            },
            error : function(data){
                console.log(data);
                alert("로그인 후 이용가능");
//                window.location.href = 'http://localhost:8080/antddun/login';
            }

            });
        };
   });

/*

    success : function(data){
             if (data == "success"){
                console.log("좋아요" + data);
                alert("좋아요");
                $('#likeBtn').attr('class','btn-light');
             }else if(data == "fail"){
                console.log("좋아요 취소" + data);
                alert("좋아요 취소");
                $('#likeBtn').attr('class','btn-danger');
             }

*/

