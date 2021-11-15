
  $(document).ready(function() {

        const username = $("#username");
        const password = $("#password");

        var regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;


        $(document).keydown(function(objEvent){
            if (objEvent.keyCode == 13 ){
                $("#btnSubmit").click();
            }
        });


        $("#btnSubmit").click(function() {

            if(username.val() == "") {
                alert("아이디를 입력해주세요.");
                username.focus();
                return false;
            } else if(username.val().match(regExp) == null){
                alert("이메일 형식으로 입력해주세요.");
                username.focus();
                return false;
            } else if (password.val() == "") {
                alert("비밀번호를 입력해주세요.");
                password.focus();
                return false;
            }
            $("#frm").submit();
        });

  });


