
$(document).ready(function() {

    var earn = parseInt($('#earn').text());
    var monthly = parseInt((earn * 10000 / 12) - (earn * 10000  * 0.009));
    var result = monthly.toLocaleString();
    var result2 = "회원정보에서 '연봉'을 입력하세요."

//    var day = Math.ceil([[${list.price}]] / (monthly * [[${list.rate}]] / 100)));

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

});

//위시 리스트
$(document).ready(function() {

    /* D-DAY 확인 버튼 */
    $('#day_btn').on('click', function (){
        var earn = parseInt($('#earn').text());
        var monthly = parseInt((earn * 10000 / 12) - (earn * 10000  * 0.009));

        var wishCost = parseInt($('#input_price').val());
        var wishPer = Number.parseInt($('#input_rate').val());
        var wishRate = Number.parseFloat(monthly * wishPer * 0.01);
        var wishDay = Number.parseInt(Math.ceil(wishCost / wishRate));
        var price = $("#input_price");
        var rate = $("#input_rate");

        if(price.val() == "") {
            alert("위시리스트 금액을 입력해주세요.");
            price.focus();
            return;
        }
        if(rate.val() == "") {
            alert("적금 입력");
            rate.focus();
            return;
        }

        $('#day_btn').hide();
        $("#show_day").show();
        $("#input_day").show();

        return $("#input_day").val(wishDay);
    });

    const wishListArr = new Array();
    const priceArr = new Array();
    const rateArr = new Array();

    const wishList = $(".input_pd").val();
    const price = $(".input_cost").val();
    const rate = $(".input_per").val();

    const btnUpdate = $('.wish_update');

    $('.wish_update').eq(1).on('click', function(){
        alert("0");
        $('.input_pd').eq(1).removeAttr("readonly");
        $('.input_cost').eq(1).removeAttr("readonly");
        $('.input_per').eq(1).removeAttr("readonly");
    });
    $('.wish_update').eq(2).on('click', function(){
        alert("1");
        $('.input_pd').eq(2).removeAttr("readonly");
        $('.input_cost').eq(2).removeAttr("readonly");
        $('.input_per').eq(2).removeAttr("readonly");
    });
    $('.wish_update').eq(3).on('click', function(){
            alert("2");
        $('.input_pd').eq(3).removeAttr("readonly");
        $('.input_cost').eq(3).removeAttr("readonly");
        $('.input_per').eq(3).removeAttr("readonly");
    });




    /* 저장 버튼 */
    $("#btn-wishSave").click(function(e) {
        e.preventDefault();
        wishListSave();

        var str = "";
        $.each(function() {
            str += "<td style='width: 10%;'><input type='radio' name='main_wish' checked /></td>";
            str += "<td style='width: 30%;'><input class='input_pd' name='wishList' th:value='${wishList.wishList}' readonly></td>";
            str += "<td style='width: 15%;'><input class='input_cost' name='price' th:value='${wishList.price}' readonly></td>";
            str += "<td style='width: 15%;'><input class='input_per' name='rate' th:value='${wishList.rate}' readonly>%</td>";
            str += "<td style='width: 13%;'><span class='wish_day'></span></td>";
            str += "<td style='width: 12%;'>";
            str += "<img class='wish_update' th:src='@{/assets/wishlist/update.png}' style='width:15px;'>";
            str += "<img class='wish_update' th:src='@{/assets/wishlist/delete.png}' style='width:15px;'>";
            str += "</td>";
        });
        $("#wishlist_read").html(str);
//          $(".dDay").html(day);
//          $(".input_pd").prop('readonly', true);

    });

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
        console.log(wishListArr);
        console.log(priceArr);
        console.log(rateArr);

        $.ajax({
            url: '/antddun/member/mypage/save',
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

            },
            error: function(data) {
                console.log(data);
                alert("실패");
            }

        }); // ajax end.
         return false;
    };

    function loadWishList() {
        $.getJSON('/antddun/member/mypage/save', function(arr) {
            console.log(arr);
            $.each(function() {
                str += "<td style='width: 10%;'><input type='radio' name='main_wish' checked /></td>";
                str += "<td style='width: 30%;'><input class='input_pd' name='wishList' th:value='${wishList.wishList}' readonly></td>";
                str += "<td style='width: 15%;'><input class='input_cost' name='price' th:value='${wishList.price}' readonly></td>";
                str += "<td style='width: 15%;'><input class='input_per' name='rate' th:value='${wishList.rate}' readonly>%</td>";
                str += "<td style='width: 13%;'>D-<span class='wish_day'></span></td>";
                str += "<td style='width: 12%;'>";
                str += "<img class='wish_update' th:src='@{/assets/wishlist/update.png}' style='width:15px;'>";
                str += "<img class='wish_update' th:src='@{/assets/wishlist/delete.png}' style='width:15px;'>";
                str += "</td>";
            });
            $("#wishlist_read").html(str);
        })
    }
});

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
                    url: "/member/mypage",
                    data: {
                        "amount" : money
                    },
                });
            } else {
                var msg = '결제에 실패하였습니다.';
                msg += '에러내용 : ' + rsp.error_msg;
            }
            alert(msg);
            document.location.href="/antddun/member/mypage/wallet";
        });
    });
});
