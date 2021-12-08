
  $(document).ready(function() {

        const username = $("#username");
        const password = $("#password");

        $('.eyeIcon').on('click',function(){
            $('#password').toggleClass('active');

            if($('#password').hasClass('active')){
                $(this).attr('class',"fa fa-eye-slash fa-lgg")
                $('#password').attr('type',"text");
            }else{
                $(this).attr('class',"fa fa-eye fa-lgg")
                $('#password').attr('type','password');
            }
        });

        const regExp = /^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i;
        const pwdReg = /^(?=.*[a-zA-Z])(?=.*[~!@#$%^&*=-])(?=.*[0-9]).{8,15}$/;

/*     엔터누르면 로그인   */
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
            //비밀번호 유효성
            } else if (password.val() == "") {
                alert("비밀번호를 입력해주세요");
                password.focus();
                return false;
            } else if (password.val().length < 8 || password.val().length > 15) {
                alert("비밀번호는 8~15자 내로 입력해주세요");
                password.focus();
                return false;
            } else if(!pwdReg.test(password.val())) {
                alert("영문, 숫자, 특수문자 포함 8~15자로 입력해주세요");
                password.focus();
                return false;
            }
            $("#frm").submit();
        });

  });


