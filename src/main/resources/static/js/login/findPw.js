$(document).ready(function() {

    $('#sendPhoneNumber').click(function(){
        let phoneNumber = $('#inputPhoneNumber').val();
        let username = $('#inputUsername').val();

        console.log("phoneNumber"+phoneNumber);

         $.ajax({
                url: "/antddun/findPw/checkUser",
                type: "POST",
                data: {
                       "phoneNum" : phoneNumber,
                       "username" : username
                       },
                dataType: "text",
                success: function(data) {
                    if(data == "실패") {
                        alert("아이디와 번호가 일치하지 않습니다.");
                    } else if(data == "성공") {
                        console.log(data);
                    }
                },
                error:function(request, status, error){
                    alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
                    }
         }) // ajax end.

        Swal.fire('인증번호 발송 완료!');

        $.ajax({
            type: "GET",
            url: "/antddun/check/sendSMS",
            data: {
                "phoneNumber" : phoneNumber
            },
            success: function(res){
                $('#checkBtn').click(function(){
                    if($.trim(res) ==$('#inputCertifiedNumber').val()){
                        Swal.fire(
                            '인증성공!',
                            '휴대폰 인증이 정상적으로 완료되었습니다.',
                            'success'
                        )

                        $.ajax({
                            type: "GET",
                            url: "/update/phone",
                            data: {
                                "phoneNumber" : $('#inputPhoneNumber').val()
                            }
                        })
                        //비밀번호 변경 칸
                        document.location.href="/antddun/changePw";
                    }else{
                        Swal.fire({
                            icon: 'error',
                            title: '인증오류',
                            text: '인증번호가 올바르지 않습니다!',
                            footer: '<a href="/antddun">다음에 인증하기</a>'
                        })
                    }
                })


            }
        })
    }) //sendPhoneNumBtn

    <!--초기화-->
    const pwd = $("#password");
    const rePwd = $("#rePassword");
    const pwdResult = $(".pwdCheckResult");

    const pwdReg = /^(?=.*[a-zA-Z])(?=.*[~!@#$%^&*=-])(?=.*[0-9]).{8,15}$/;

    pwd.blur(function(e) {
        const pwdResult = $(".pwdCheckResult");

        if(pwd.val().length == 0 || pwd.val().length > 15) {
            pwdResult.html("영문, 숫자, 특수문자 조합 8~15자");
            return false;
        } else if(!pwdReg.test(pwd.val())) {
            pwdResult.html("영문, 숫자, 특수문자 조합 8~15자");
            return false;
        } else {
            pwdResult.html("알맞은 비밀번호입니다.");
            pwdResult.css("color","blue");
            return true;
        }
    });

    rePwd.blur(function(e) {
        const rePwdCheck = $(".rePwdCheckResult");

        if(pwd.val() != rePwd.val()) {
            rePwdCheck.html("비밀번호가 일치하지 않습니다.");
            return false;
        } else {
            rePwdCheck.html("비밀번호가 일치합니다");
            rePwdCheck.css("color","blue");
            return true;
        }
    });

    $('#changePw').click(function(){
        if(!pwdReg.test(pwd.val())) {
            pwdResult.html("영문, 숫자, 특수문자 조합 8~15자");
            pwd.focus();
            return false;
        }

        if(rePwd.val() == "") {
            rePwdCheck.html("비밀번호를 재확인해주세요.")
            rePwd.focus();
            return false;
        }

        if(pwd.val() != rePwd.val()) {
            rePwdCheck.html("비밀번호를 재확인해주세요.")

            pwd.val() = "";
            rePwd.val() = "";
            pwd.focus();
            return false;
        }

         $.ajax({
                url: "/antddun/phoneNumCheck",
                type: "POST",
                dataType: "JSON",
                data: {
                       "phoneNum" : $("#phoneNum").val()+"",
                       "email" : $("#username").val()
                       },
                success: function(data) {
                    if(data == 1) {
                        alert("전화번호 중복");
                    } else if(data == 0) {
                        alert("전화번호 사용 가능");
                        console.log(data);
                        console.log($('#phoneNum').val());
                    }
                }
         }) // ajax end.

    })

});
