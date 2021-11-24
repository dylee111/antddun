
  $(document).ready(function() {
    var contextPath = $('#contextPathHolder').attr('data-contextPath') ? $('#contextPathHolder').attr('data-contextPath') : '';
    var qnaNo = $('#qnaNo').text();
    var likesCnt = $('#likesCnt');
    var likeBtn = $('#likeBtn');
    var likeBtns = $('#likeBtns');


    $('#likeBtns').click(function(){
        var likeBtnimg = $(this).children("span").children("img");

        likeBtnimg.attr("src", function(index, attr){
            if(attr.match('_1')){
                return attr.replace('_1', '_0');
            } else {
                return attr.replace('_0', '_1');
            }
        });
    });

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



                        } else { //좋아요, db에 있는 개수 반영
                            likeBtn.text(JSON.stringify(data.likesCnt));
                            likeBtns.removeAttr();
                            likeBtns.attr('src','@{/assets/heart_1.png}');

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
        $.ajax({
            url : contextPath + "/qna/list/" + qnaNo,
            type : 'POST',
            data: qnaNo,
            dataType: 'text',
            success : function(data){
                        if ( data == 0 ) { //좋아요 해제
                        } else { //좋아요
                            likesBtn.addClass("like_on");
                        }
            },
            error : function(data){
                console.log(data);
            }

        });
*/

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

