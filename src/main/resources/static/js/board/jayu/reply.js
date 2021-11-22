$(document).ready(function() {
 var contextPath = $('#contextPathHolder').attr('data-contextPath') ? $('#contextPathHolder').attr('data-contextPath') : '';

            var jayuNo = [[${jayuBoardDTO.jayuNo}]];
            var text = $('textarea[name="text"]');

            <!--댓글 등록-->
            $('.reviewSaveBtn').click(function(){
                var data = {jayuNo:jayuNo, jayureply:text.val()};
                console.log(url);
                $.ajax({
                    url: contextPath+'/reply/'+jayuNo,
                    type: "POST",
                    data:JSON.stringify(data),
                    contentType:"application/json; charset=utf-8",
                    dataType: "text",
                    success: function(data){
                        console.log("result: " + data);
                        self.location.reload();
                        }
                    });//ajax
                }); //click
//
//
//            var memberNum = [[${member.memberNum}]];
//            var storeNum = [[${dto.storeNum}]];
//            var review = $('textarea[name="reviewText"]');
//
//            //리뷰 데이터 넘기기
//            $("#rv-btn").click(function(){
//                alert("benny");
//                var data = {
//                    memberNum:memberNum,
//                    storeNum:storeNum,
//                    text:review.val()
//                    };
//                console.log(">>>>"+data);
//
//                $.ajax({
//                    url: contextPath+'/addReview/'+storeNum,
//                    type:"POST",
//                    data:JSON.stringify(data),
//                    contentType:"application/json; charset=utf-8",
//                    dataType:"text",
//                    success: function(data){
//                        console.log("result: "+data);
//                        var reviewNum = parseInt(data);
//                        console.log("result: "+reviewNum);
//                        self.location.reload();
//                     },
//                    error:function(data){
//                  alert("errorrrrrrrrrrrrrrrr");
//                    }
//                });
//            });
//
//            //리뷰 리스트 보기
//            function getStoreReviews() {
//                function formatTime(str) {
//                    var date = new Date(str);
//                    return date.getFullYear()+'/'+
//                    (date.getMonth() + 1)+'/' +
//                    date.getDate() + ' ' +
//                    date.getHours()+':'+
//                    date.getMinutes();
//                }
//
//                $.getJSON(contextPath+"/addReview/"+storeNum+"/list", function(arr) {
//                var str = "";
//
//                    $.each(arr, function(idx, review) {
//                    console.log("review>>>>>>>>>>>"+review);
//                  str += ' <div class="card-body" data-reviewNum="'+review.reviewNum+'">';
//                        str += ' <b class="reviewNum">'+review.reviewNum+'</b>';
//                        str += ' <h5 class="reviewText">'+review.text+'</h5>';
//                        str += ' <p>'+formatTime(review.regDate)+'</p>';
//                        str += ' </div>';
//                    });
//
//                    $(".rv-list-table").html(str);
//                    //console.log(">>>>>>>>>>>"+str);
//                });
//                }
//                getStoreReviews();
//
});
