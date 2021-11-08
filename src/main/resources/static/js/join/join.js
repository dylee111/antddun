    $(document).ready(function() {

//      초기화
        const id = $("#username");
        const pwd = $("#password");
        const rePwd = $("#rePassword");
        const firstName = $("#firstName");
        const lastName = $("#lastName");
        const phoneNum = $("#phoneNum");

        const name = $("#firstName");
        const result = $(".nameCheckResult");
        const pwdResult = $(".pwdCheckResult");
        const idResult = $(".idCheckResult");
        const nameResult = $(".nameCheckResult");
        const mobileResult = $(".mobileCheckResult");

        const pwdReg = /^(?=.*[a-zA-Z])(?=.*[!@#$%^&*=-])(?=.*[0-9]).{8,15}$/;


//        <!--blur 이벤트 / 유효성-->

        pwd.blur(function(e) {
            const pwdResult = $(".pwdCheckResult");

            if(pwd.val().length == 0 || pwd.val().length > 15) {
                pwdResult.html("영문, 숫자, 특수문자 조합 8~15자");
                pwdResult.css("font-size","8px")
                pwdResult.css("color","red")
                return false;
            } else if(!pwdReg.test(pwd.val())) {
                pwdResult.html("영문, 숫자, 특수문자 조합 8~15자");
                pwdResult.css("font-size","8px")
                pwdResult.css("color","red")
                return false;
            } else {
                pwdResult.html("알맞은 비밀번호입니다.");
                pwdResult.css("font-size","8px")
                pwdResult.css("color","blue")
                return true;
            }
        });

        rePwd.blur(function(e) {
            const rePwdCheck = $(".rePwdCheckResult");

            if(pwd.val() != rePwd.val()) {
                rePwdCheck.html("비밀번호가 일치하지 않습니다.");
                rePwdCheck.css("font-size","8px")
                rePwdCheck.css("color","red")
                return false;
            } else {
                rePwdCheck.html("비밀번호가 일치합니다");
                rePwdCheck.css("font-size","8px")
                rePwdCheck.css("color","blue")
                return true;
            }
        });

        name.onblur = function(e) {
            if(name.val().length > 3) {
                mobileResult.html("3글자를 초과할 수 없습니다.");
                mobileResult.css("font-size","8px")
                mobileResult.css("color","red")
                return false;
            } else if(name.val().length == 0) {
                mobileResult.html("이름을 입력해주세요.");
                mobileResult.css("font-size","8px")
                mobileResult.css("color","red")
                return false;
            } else if(name.val().length > 0 && name.val().length <= 2) {
                mobileResult.html( "");
                return true;
            }

        }

//        <!--option 태그 추가-->

        for(var i = 1; i < 50; i++) {
            $(".experience").append("<option value="+i+" name='experience'>"+i+"</option>");
        }

        for(var i = 0; i <= 47; i++) {
            var hour = "";
            var min = ":00";

            if((Math.ceil(i/2)) < 13) {
                hour = Math.floor(i/2);
            } else {
                hour = Math.floor(i/2);
            }
            if((Math.ceil(i%2)) != 0) { <!--나머지 0 또는 1-->
                var min = ":30";
            }

//<!--            $(".startTime").append("<option value="+hour+min">"+hour+min+"</option>");-->
            $(".startTime").append("<option>" + ((hour >= 10) ? hour : ("0"+hour)) + min +"</option>" );
        }

        for(var i = 47; i >= 0; i--) {
            var hour = "";
            var min = ":00";

            if((Math.ceil(i/2)) < 13) {
                hour = Math.floor(i/2);
            } else {
                hour = Math.floor(i/2);
            }
            if((Math.ceil(i%2)) != 0) { <!--나머지 0 또는 1-->
                var min = ":30";
            }

            $(".endTime").append("<option>" + ((hour >= 10) ? hour : ("0"+hour)) + min +"</option>" );
        }

//        <!--아이디 / 연락처 / 추천인 중복체크-->


       id.blur(function() {

            if(id.val().length == 0) {
                idResult.html("아이디를 입력해주세요.");
                idResult.css("font-size","8px")
                idResult.css("color","red")
                id.focus();
                return false;
            }
            idDuplicateCheck();
        }); // btnCheck event end.

        $("#btnPhone").click(function() {
            if($("#phoneNum").val() == "") {
                mobileResult.html("연락처를 입력해주세요.")
                mobileResult.css("font-size","8px")
                mobileResult.css("color","red")
                phoneNum.focus();
                return false;
            }

            $.ajax({
                url: "/antddun/phoneNumCheck",
                type: "POST",
                dataType: "JSON",
                contentType: 'application/json',
                data: {"phoneNum" : $("#phoneNum").val()},
                success: function(data) {
                    console.log(data);
                    if(data == 1) {
                        alert("전화번호 중복");
                    } else if(data == 0) {
                        alert("전화번호 사용 가능");
                    }
                }
            }) // ajax end.
        }); // phoneAuthBtn event end.

//        <!--유효성 검사-->

        $(".btnJoin").click(function() {
            idDuplicateCheck();

            if(id.val() == "") {
                idResult.innerHTML = "아이디를 입력해주세요.";
                idResult.style.color = "red";
                id.focus();
                return false;
            } else if(idCheckResult.value == "중복된 아이디입니다.") {
                id.focus();
                return;
            }

            if(!pwdReg.test(pwd.val())) {
                pwdResult.html("영문, 숫자, 특수문자 조합 8~15자");
                pwdResult.css("font-size","8px")
                pwdResult.css("color","red")
                pwd.focus();
                return false;
            }

            if(rePwd.val() == "") {
                rePwdCheck.html("비밀번호를 재확인해주세요.")
                rePwdCheck.css("font-size","8px")
                rePwdCheck.css("color","red")
                rePwd.focus();
                return false;
            }

            if(pwd.val() != rePwd.val()) {
                rePwdCheck.html("비밀번호를 재확인해주세요.")
                rePwdCheck.css("font-size","8px")
                rePwdCheck.css("color","red")
                pwd.val() = "";
                rePwd.val() = "";
                pwd.focus();
                return false;
            }

            if(firstName.val() == "") {
                nameResult.html("이름(성)을 입력해주세요.")
                nameResult.css("font-size","8px")
                nameResult.css("color","red")
                firstName.focus();
                return false;
            }

            if(lastName.val() == "") {
                nameResult.html("이름을 입력해주세요.")
                nameResult.css("font-size","8px")
                nameResult.css("color","red")
                lastName.focus();
                return false;
            }

            if(phoneNum.val() == "") {
                mobileResult.html("연락처를 입력해주세요.")
                mobileResult.css("font-size","8px")
                mobileResult.css("color","red")
                phoneNum.focus();
                return false;
            }
            $("#frm").submit();
        });

        function onlyNum(e) {
            if(event.keyCode < 48 || event.keyCode > 57) { event.returnValue = false;}
        }

        function idDuplicateCheck() {
            $.ajax({
                url: "/antddun/idCheck",
                type: "POST",
                dataType: "JSON",
                contentType: 'application/json',
                data: {"email" : id.val()},
                success: function(data) {
                    console.log(data);
                    if(data == 1) {
                        idResult.innerHTML = "중복된 아이디입니다.";
                        idResult.style.color = "red";
                        idResult.style.fontSize = "8px";
                        id.focus();
                        return;
                    } else if(data == 0) {
                        idResult.innerHTML = "사용 가능한 아이디입니다.";
                        idResult.style.color = "blue";
                        idResult.style.fontSize = "8px";
                    }
                }
            }) // ajax end.
        } // idDuplicateCheck() end

    });