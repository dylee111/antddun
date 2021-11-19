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
    }); // end

    $('#money_tab').click(function(){
        $('#money_tab').css("color", '#f9c74f');
        $('#my_tab').css("color", '#ededed');
        $('#money_page').show();
        $('#my_page').hide();
    }); // end

    // 실수령 계산기
    if(result == "NaN") {
        return $('#earn_month').val(result2);
    } else {
        return $('#earn_month').val(result);
    }

}); // document.ready end

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
    }); // end



    const btnUpdate = $('.wish_update');

    var count_0 = 0;
    /* 수정 버튼 이벤트 */
    $('#btn-update0').on('click', function(){
        count_0++;
        if(count_0 == 1){
        console.log(count_0);
            $('#price0').removeAttr("readonly");
            $('#rate0').removeAttr("readonly");
            $('#list0').removeAttr("readonly");
        } else if(count_0 == 2) {
                console.log(count_0);

            alert("돌아감");
            var wno = $("#wno0").val();

            var wishList = {
                wno: wno,
                wishList: $("#list0").val(),
                price: $("#price0").val(),
                rate: $("#rate0").val()
            }

            $.ajax({
                url: '/antddun/member/mypage/modify/' + wno,
                method: 'put',
                data: JSON.stringify(wishList),
                contentType: 'application/json; charset=utf-8',
                success: function(result) {
                    if(result === 'modify') {
                        alert("수정완료");
                        $('#price0').attr("readonly");
                        $('#rate0').attr("readonly");
                        $('#list0').attr("readonly");
                        count_0 = 0;
                    }
                }
            })
        }

    }); // modify() end

    $('#btn-update1').on('click', function(){
        $('#list1').removeAttr("readonly");
        $('#price1').removeAttr("readonly");
        $('#rate1').removeAttr("readonly");
    });
    $('#btn-update2').on('click', function(){
        $('#list2').removeAttr("readonly");
        $('#price2').removeAttr("readonly");
        $('#rate2').removeAttr("readonly");
    }); // end

    /* 삭제 버튼 이벤트 */
    $('#btn-delete0').on('click', function(){
        var wno = $("#wno0").val();
        $.ajax({
            url: '/antddun/member/mypage/delete/' + wno,
            method: 'delete',
            success: function(result) {
                if(result == "delete") {
                    alert(wno + "삭제 OK");
                }
            }
        })
    }); // end

    /* 저장 버튼 */
    $("#btn-wishSave").click(function(e) {
        e.preventDefault();
        wishListSave();
    });
}); // document.ready2 end

        const wishListArr = new Array();
        const priceArr = new Array();
        const rateArr = new Array();

        const wishList = $(".input_pd").val();
        const price = $(".input_cost").val();
        const rate = $(".input_per").val();

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
                load();
            },
            error: function(data) {
                console.log(data);
                alert("실패");
            }

        }); // ajax end.
         return false;
    }; // wishListSave() end

//    function load() {
//        $.ajax({
//            url: '/antddun/member/mypage',
//            type: 'get',
//            dataType: 'text',
//            success: function() {
//                var str = "<td style='width: 10%;'><input type='radio' name='main_wish' checked /></td>"+
//                "<td style='width: 30%;'><input th:id="'list''+${index.index}'" class='input_pd' name='wishList' th:value='${wishList.wishList}' readonly></td>"+
//                "<td style='width: 15%;'><input th:id="'price'"+"'+${index.index}'" class='input_cost' name='price' th:value='${wishList.price}' readonly></td>"+
//                "<td style='width: 15%;'><input th:id="'rate'"+"'+${index.index}'"  class='input_per' name='rate' th:value='${wishList.rate}' readonly>%</td>"+
//                "<td style='width: 13%;'><span class='wish_day'></span></td>"+
//                "<td style='width: 12%;'>"+
//                "<img th:id="'btn-update'"+"'+${index.index}'" class='wish_update' th:src='@{/assets/wishlist/update.png}' style='width:15px;'>"+
//                "<img th:id="'btn-delete'"+"'+${index.index}'" class='wish_update' th:src='@{/assets/wishlist/delete.png}' style='width:15px;'>"
//                "</td>";
//                $("#wishlist_read").append(str);
//            }
//        })
//    } // end


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

