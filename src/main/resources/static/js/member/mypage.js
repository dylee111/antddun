$(document).ready(function() {
    var earn = parseInt($('#earn').text());
    var monthly = parseInt((earn * 10000 / 12) - (earn * 10000  * 0.009));
    var result = monthly.toLocaleString();
    var result2 = "회원정보에서 '연봉'을 입력하세요."

    //Tab 전환 효과
//    $('#my_tab').click(function(){
//        $('#my_tab').css("color", '#f9c74f');
//        $('#money_tab').css("color", '#ededed');
//        $('#my_page').show();
//        $('#money_page').hide();
//    });
//
//    $('#money_tab').click(function(){
//        $('#money_tab').css("color", '#f9c74f');
//        $('#my_tab').css("color", '#ededed');
//        $('#money_page').show();
//        $('#my_page').hide();
//    });

    // 실수령 계산기
    if(result == "NaN") {
        return $('#earn_month').val(result2);
    } else {
        return $('#earn_month').val(result);
    }

}); // document.ready end

//위시 리스트 D-Day
$(document).ready(function() {
    var earn = parseInt($('#earn').text());
    var monthly = (earn * 10000 / 12) - (earn * 10000  * 0.009);

    $(".wish_cal").keyup(function(){
        var price = parseInt($("#input_price").val()) == NaN ? 0 : parseInt($("#input_price").val());
        var rate = parseInt($("#input_rate").val()) == NaN ? 0 : parseInt($("#input_rate").val());

        var wishDay = parseInt(Math.ceil(price / (monthly * rate * 0.01)));

        if ($("#input_rate").val() != "" && $("#input_rate").val() != ""){
                $("#input_day").val(parseInt(wishDay));
        }
    });

    $("#price0").keyup(function(){
        var price0 = parseInt($("#price0").val()) == NaN ? 0 : parseInt($("#price0").val());
        var rate0 = parseInt($("#rate0").val()) == NaN ? 0 : parseInt($("#rate0").val());

        var wishDay0 = parseInt(Math.ceil(price0 / (monthly * rate0 * 0.01)));

        if ($("#price0").val() != "" && $("#price0").val() != ""){
                $("#dDay0").val(parseInt(wishDay0));
        }
    });
    $("#rate0").keyup(function(){
        var price0 = parseInt($("#price0").val()) == NaN ? 0 : parseInt($("#price0").val());
        var rate0 = parseInt($("#rate0").val()) == NaN ? 0 : parseInt($("#rate0").val());

        var wishDay0 = parseInt(Math.ceil(price0 / (monthly * rate0 * 0.01)));

        if ($("#price0").val() != "" && $("#price0").val() != ""){
                $("#dDay0").val(parseInt(wishDay0));
        }
    });

    $("#price1").keyup(function(){
        var price1 = parseInt($("#price1").val()) == NaN ? 0 : parseInt($("#price1").val());
        var rate1 = parseInt($("#rate1").val()) == NaN ? 0 : parseInt($("#rate1").val());

        var wishDay1 = parseInt(Math.ceil(price1 / (monthly * rate1 * 0.01)));

        if ($("#price1").val() != "" && $("#price1").val() != ""){
                $("#dDay1").val(parseInt(wishDay1));
        }
    });
    $("#rate1").keyup(function(){
        var price1 = parseInt($("#price1").val()) == NaN ? 0 : parseInt($("#price1").val());
        var rate1 = parseInt($("#rate1").val()) == NaN ? 0 : parseInt($("#rate1").val());

        var wishDay1 = parseInt(Math.ceil(price1 / (monthly * rate1 * 0.01)));

        if ($("#price1").val() != "" && $("#price1").val() != ""){
                $("#dDay1").val(parseInt(wishDay1));
        }
    });

    $("#price2").keyup(function(){
        var price2 = parseInt($("#price2").val()) == NaN ? 0 : parseInt($("#price2").val());
        var rate2 = parseInt($("#rate2").val()) == NaN ? 0 : parseInt($("#rate2").val());

        var wishDay2 = parseInt(Math.ceil(price2 / (monthly * rate2 * 0.01)));

        if ($("#price2").val() != "" && $("#price2").val() != ""){
                $("#dDay2").val(parseInt(wishDay2));
        }
    });
    $("#rate2").keyup(function(){
        var price2 = parseInt($("#price2").val()) == NaN ? 0 : parseInt($("#price2").val());
        var rate2 = parseInt($("#rate2").val()) == NaN ? 0 : parseInt($("#rate2").val());

        var wishDay2 = parseInt(Math.ceil(price2 / (monthly * rate2 * 0.01)));

        if ($("#price2").val() != "" && $("#price2").val() != ""){
                $("#dDay2").val(parseInt(wishDay2));
        }
    });
//});

    //위시리스트 수정 & 삭제

    $('#btn-update0').on('click', function(){
        count++;
        if(count == 1){
            console.log(count);

            $('#price0').removeAttr("readonly");
            $('#rate0').removeAttr("readonly");
            $('#list0').removeAttr("readonly");
            $(this).css("filter", "brightness(1)");
            $('#list0').focus();
        } else if(count == 2) {
            console.log(count);
            $(this).css("filter", "brightness(0)");
            var wno = $("#wno0").val();
            console.log(wno);

            console.log("수정시 dDay0 : "+$('#dDay0').val());
            var tmp = $('#dDay0').val();
            console.log("tmp:"+tmp)
            var wishLists = {
                wno: wno,
                wishList: $("#list0").val(),
                price: $("#price0").val(),
                rate: $("#rate0").val(),
                dDay: $("#dDay0").val()
            }
            console.log("wishList : "+JSON.stringify(wishLists));

            $.ajax({
                url: '/antddun/member/mypage/wishlist/modify/' + wno,
                method: 'put',
                dataType: 'json',
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(wishLists),
                success: function(result) {
                    if(result === 'modify') {
                        alert("수정완료"+ wno);
                        $('#price0').attr("readonly");
                        $('#rate0').attr("readonly");
                        $('#list0').attr("readonly");
                        count = 0;
                        self.location.reload();
                    }
                }
            })
        } // count == 2
    }); // modify() end
    $('#btn-update1').on('click', function(){
        count++;
        if(count == 1){
            $('#price1').removeAttr("readonly");
            $('#rate1').removeAttr("readonly");
            $('#list1').removeAttr("readonly");
            $(this).css("filter", "brightness(1)");
            $('#list1').focus();
        } else if(count == 2) {
                console.log(count);
            $(this).css("filter", "brightness(0)");
            var wno = $("#wno1").val();
            console.log(wno);
            var wishList = {
                wno: wno,
                wishList: $("#list1").val(),
                price: $("#price1").val(),
                rate: $("#rate1").val(),
                dDay: $("#dDay1").val()
            }

            $.ajax({
                url: '/antddun/member/mypage/wishlist/modify/' + wno,
                method: 'put',
                data: JSON.stringify(wishList),
                contentType: 'application/json; charset=utf-8',
                success: function(result) {
                    if(result === 'modify') {
                        alert("수정완료"+ wno);
                        $('#price1').attr("readonly");
                        $('#rate1').attr("readonly");
                        $('#list1').attr("readonly");
                        count = 0;
                        self.location.reload();
                    }
                }
            })
        } // count == 2
    });
    $('#btn-update2').on('click', function(){
        count++;
        if(count == 1){
        console.log(count);
            $('#price2').removeAttr("readonly");
            $('#rate2').removeAttr("readonly");
            $('#list2').removeAttr("readonly");
            $(this).css("filter", "brightness(1)");
            $('#list2').focus();
        } else if(count == 2) {
                console.log(count);
            $(this).css("filter", "brightness(0)");
            var wno = $("#wno2").val();
            console.log(wno);
            var wishList = {
                wno: wno,
                wishList: $("#list2").val(),
                price: $("#price2").val(),
                rate: $("#rate2").val(),
                dDay: $("#dDay2").val()
            }

            $.ajax({
                url: '/antddun/member/mypage/wishlist/modify/' + wno,
                method: 'put',
                data: JSON.stringify(wishList),
                contentType: 'application/json; charset=utf-8',
                success: function(result) {
                    if(result === 'modify') {
                        alert("수정완료"+ wno);
                        $('#price2').attr("readonly");
                        $('#rate2').attr("readonly");
                        $('#list2').attr("readonly");
                        count = 0;
                        self.location.reload();
                    }
                }
            })
        } // count == 2
    }); // end

    /* 삭제 버튼 이벤트 */
    $('#btn-delete0').on('click', function(){
        var wno = $("#wno0").val();
        $.ajax({
            url: '/antddun/member/mypage/wishlist/delete/' + wno,
            method: 'delete',
            success: function(result) {
                if(result == "delete") {
                    alert(wno + "삭제 OK");
                }
            self.location.reload();
            }
        })
    }); // end
    $('#btn-delete1').on('click', function(){
        var wno = $("#wno1").val();
        $.ajax({
            url: '/antddun/member/mypage/wishlist/delete/' + wno,
            method: 'delete',
            success: function(result) {
                if(result == "delete") {
                    alert(wno + "삭제 OK");
                }
            self.location.reload();
            }
        })
    }); // end
    $('#btn-delete2').on('click', function(){
        var wno = $("#wno2").val();
        $.ajax({
            url: '/antddun/member/mypage/wishlist/delete/' + wno,
            method: 'delete',
            success: function(result) {
                if(result == "delete") {
                    alert(wno + "삭제 OK");
                }
            self.location.reload();
            }
        })
    }); // end

    /* 저장 버튼 */
    $("#btn-wishSave").click(function(e) {
        e.preventDefault();
        wishListSave();
    });

    /* 회원 정보 수정 */
    $("#btn_member_modify").click(function(){
        var infoMno = $("#myMno").val();
        var myInfo = {
            mno: infoMno,
            username: $("#username").val(),
            password: $("#password").val(),
            phoneNum: $("#phoneNum").val(),
            job: $(".job").val(),
            experience: $(".experience").val(),
            salary: $("#salary").val(),
            startTime: $(".startTime").val(),
            endTime: $(".endTime").val()
        }
        console.log(myInfo);
        $.ajax({
            url: '/antddun/member/mypage/info/modify/' + infoMno,
            method: 'post',
            data: JSON.stringify(myInfo),
            contentType: 'application/json; charset=utf-8',
            success: function(result) {

                    alert("수정완료"+ infoMno);

            },
            error: function(request, status, error) {
                alert("ERROR>> "+error +" request>> "+request+"\n"+"status>>"+status );
            }
        })
    });
}); // document.ready2 end

    const wishListArr = new Array();
    const priceArr = new Array();
    const rateArr = new Array();
    const dDayArr = new Array();

    const wishList = $(".input_pd").val();
    const price = $(".input_cost").val();
    const rate = $(".input_per").val();

    const btnUpdate = $('.wish_update');

    /* 위시리스트 저장 */
    function wishListSave() {
        $("input[name=wishList]").each(function(index, wish) {
            wishListArr.push($(wish).val());
        });
        $("input[name=price]").each(function(index, p) {
            priceArr.push($(p).val());
        });
        $("input[name=rate]").each(function(index, r) {
            rateArr.push($(r).val());
        });
        $("input[name=dDay]").each(function(index, d) {
            dDayArr.push($(d).val());
        });

        console.log(wishListArr);
        console.log(priceArr);
        console.log(rateArr);
        console.log(dDayArr);

        $.ajax({
            url: '/antddun/member/mypage/wishlist/save',
            type: 'POST',
            dataType: 'text',
            data: {
                  member: $("#member_mno").val(),
                  wishList: $("#input_list").val(),
                  price: $("#input_price").val(),
                  rate: $("#input_rate").val(),
                  dDay: $("#input_day").val()
            },
            success: function(data) {
                if(data < 3) {
                    alert("등록되었습니다.");
                } else if(data >= 3) {
                    alert("위시 리스트는 3개만 작성가능");
                    return false;
                }
                self.location.reload();
            },
            error: function(data) {
                console.log(data);
                alert("실패");
            }

        }); // ajax end.
         return false;
    }; // wishListSave() end



//뚠 충전
$(document).ready(function(){
    $(".charge_btn").click(function(){
        $('.charge_container').show();
        $('.dim').show();
    });

    $('.charge_close').click(function(){
        $('.charge_container').hide();
        $('.dim').hide();
    });

    $("#charge_kakao").click(function(){
        var IMP = window.IMP;
        IMP.init('imp50037536');
        var money = $('input[name="cp_item"]:checked').val();
        console.log(money);

        IMP.request_pay({
            pg : 'Kakao Pay',
            merchant_uid: 'ddun_' + new Date().getTime(), // 상점에서 관리하는 주문 번호
            name : '뚠 충전하기', //결제창에서 보여질 이름
            amount : money,
            buyer_email : '${member.username}',
            buyer_name : '${member.lastName}'+'${member.firstName}',
            buyer_tel : '${member.phoneNum}',
            buyer_addr : '부산광역시 개미는뚠뚠 빌딩',
            buyer_postcode : '123-456'
        }, function(rsp) {
            if ( rsp.success ) {
                var msg = '결제가 완료되었습니다.';
                msg += '\n고유ID : ' + rsp.imp_uid;
                msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                msg += '\n결제 금액 : ' + rsp.paid_amount;
                msg += '카드 승인번호 : ' + rsp.apply_num;

                $.ajax({
                    type: "GET",
                    url: "/antddun/member/mypage/wallet/save",

                    data: {
                        "amount" : money
                    },
                    success: function(data) {
                        console.log(data);
                    }
                });
            } else {
                var msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
            }
            alert(msg);
            document.location.href="/antddun/member/mypage/wallet";
        });
    }); // 아임포트 end;

});


