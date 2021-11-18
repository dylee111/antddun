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
