
  $(document).ready(function() {
        var contextPath = $('#contextPathHolder').attr('data-contextPath') ? $('#contextPathHolder').attr('data-contextPath') : '';
        const phoneNum = $("#phoneNum");
        const result = $("#result");

        $("#phoneNum").keyup( function(){
                $(this).val( $(this).val().replace(/[^0-9]/gi,"") );
            });

        $("#btnFindId").click(function() {

            $.ajax({
                data: {"phoneNum" : $("#phoneNum").val()}, //controller로 데이터 보냄(매개변수)
                type: "POST", //여기서 post라고 지정해줌 form태그가 아니라 / data를 보낼 타입을 지정!
                url: contextPath + '/findUsername', //controller에서 username을 여기로 보냄 데이터를 받아올 주소
                dataType: "text", //결과가 json으로 넘어옴
                success: function(data) { //controller 거쳐온 username
                           result.html(data);
                }

            }) // ajax end.
        }); // phoneAuthBtn event end.
    }); //document end.