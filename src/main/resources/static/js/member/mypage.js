$(document).ready(function() {

    var earn = parseInt($('#earn').text());
    var monthly = parseInt((earn * 10000 / 12) - (earn * 10000  * 0.009));
    var result = monthly.toLocaleString();
    var result2 = "회원정보에서 '연봉'을 입력하세요."

//    var day = Math.ceil([[${list.price}]] / (monthly * [[${list.rate}]] / 100)));

    //Tab 전환 효과

    $('#my_tab').click(function(){
        $('#my_tab').css("color", '#f9c74f');
        $('#money_tab').css("color", '#ededed');
        $('#my_page').show();
        $('#money_page').hide();
    });

    $('#money_tab').click(function(){
        $('#money_tab').css("color", '#f9c74f');
        $('#my_tab').css("color", '#ededed');
        $('#money_page').show();
        $('#my_page').hide();
    });

    const wishListArr = new Array();
    const priceArr = new Array();
    const rateArr = new Array();

    const wishList = $(".input_pd").val();
    const price = $(".input_cost").val();
    const rate = $(".input_per").val();


    /* 저장 버튼 */
    $("#btn-save1").click(function() {
        wishListSave(0);
        $("#btn-save1").hide();
        $("#btn-update1").show();
        $("#btn-delete1").show();
        $(".dDay").html(day);
//        $(".input_pd").prop('readonly', true);
    });
    $("#btn-save2").click(function() {
        wishListSave(1);
        $("#btn-save2").hide();
        $("#btn-update2").show();
        $("#btn-delete2").show();

    });
    $("#btn-save3").click(function() {
        wishListSave(2);
        $("#btn-save3").hide();
        $("#btn-update3").show();
        $("#btn-delete3").show();
    });

    /* 위시리스트 저장 */
    function wishListSave(idx) {
        $("input[name=wishList]").each(function(index, wish) {
            wishListArr.push($(wish).val());
        });
        $("input[name=price]").each(function(index, p) {
            priceArr.push($(p).val());
        });
        $("input[name=rate]").each(function(index, r) {
            rateArr.push($(r).val());
        });
        console.log(wishListArr);
        console.log(priceArr);
        console.log(rateArr);

        $.ajax({
            url: '/antddun/member/mypage/save',
            type: 'POST',
            dataType: 'text',
            data: {
//                wishList: $(".input_pd").val(),
//                price: $(".input_cost").val(),
//                rate: $(".input_per").val()
                  wishList: $("input[name='wishList']").eq(idx).val(),
                  price: $("input[name='price']").eq(idx).val(),
                  rate: $("input[name='rate']").eq(idx).val()
            },
            success: function(data) {
                alert("성공");
                $(this).submit();
//                $('#money_page').show();
//                $('#my_page').hide();
            },
            error: function() {
                console.log(data);
                alert("실패");
            }

        }); // ajax end.
         return false;
    };


// 실수령 계산기

    if(result == "NaN") {
        return $('#earn_month').val(result2);
    } else {
        return $('#earn_month').val(result);
    }
});

//위시 리스트
$(document).ready(function() {
    $('.wish_update').on('click', function(){
        $('.input_pd').removeAttr("readonly");
        $('.input_cost').removeAttr("readonly");
        $('.input_per').removeAttr("readonly");
    });
});

//뚠 충전
$(".charge_btn").click(function(){
    var IMP = window.IMP;
    IMP.init('imp02152838');
    IMP.request_pay({
        pg : '이니시스',
        pay_method : 'card',
        merchant_uid: 'charge_' + new Date().getTime(), // 상점에서 관리하는 주문 번호
        name : '뚠 충전하기', //결제창에서 보여질 이름
        amount : 14000, //가격
        buyer_email : '${member.username}',
        buyer_name : '${member.lastName}'+'${member.firstName}',
        buyer_tel : '${member.phoneNum}',
        buyer_addr : '부산광역시 개미는뚠뚠 빌딩',
        buyer_postcode : '123-456'
    }, function(rsp) {
        if ( rsp.success ) {
            //[1] 서버단에서 결제정보 조회를 위해 jQuery ajax로 imp_uid 전달하기
            jQuery.ajax({
                url: "/payments/complete", //cross-domain error가 발생하지 않도록 주의해주세요
                type: 'POST',
                dataType: 'json',
                data: {
                    imp_uid : rsp.imp_uid
                    //기타 필요한 데이터가 있으면 추가 전달
                }
            }).done(function(data) {
                //[2] 서버에서 REST API로 결제정보확인 및 서비스루틴이 정상적인 경우
                if ( everythings_fine ) {
                    var msg = '결제가 완료되었습니다.';
                    msg += '\n고유ID : ' + rsp.imp_uid;
                    msg += '\n상점 거래ID : ' + rsp.merchant_uid;
                    msg += '\결제 금액 : ' + rsp.paid_amount;
                    msg += '카드 승인번호 : ' + rsp.apply_num;

                    alert(msg);
                } else {
                    //[3] 아직 제대로 결제가 되지 않았습니다.
                    //[4] 결제된 금액이 요청한 금액과 달라 결제를 자동취소처리하였습니다.
                }
            });
        } else {
            var msg = '결제에 실패하였습니다.';
            msg += '에러내용 : ' + rsp.error_msg;

            alert(msg);
        }
    });
});

