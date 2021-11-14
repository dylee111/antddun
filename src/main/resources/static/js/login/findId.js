  $(document).ready(function() {
        const phoneNum = $("#phoneNum");
        const result = $("#result");

        $("#btnFindId").click(function() {
            $.ajax({
                data: {"phoneNum" : $("#phoneNum").val()}, //controller로 데이터 보냄(매개변수)
                url: "/antddun/findUsername", //username을 여기로 보냄
                type: "POST",
                dataType: "JSON",
                success: function(data) { //controller 거쳐온 username
                    if(data != null) {
                       result.html(data);
                    } else if(data == null) {
                        alert("일치하는 아이디가 없습니다.");
                    }
                }
            }) // ajax end.
        }); // phoneAuthBtn event end.
    }); //document end.