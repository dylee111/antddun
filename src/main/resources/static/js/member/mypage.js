
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





// 실수령 계산기

    if(result == "NaN") {
        return $('#earn_month').val(result2);
    } else {
        return $('#earn_month').val(result);
    }
});

//위시 리스트
$(document).ready(function() {

    const wishListArr = new Array();
    const priceArr = new Array();
    const rateArr = new Array();

    const wishList = $(".input_pd").val();
    const price = $(".input_cost").val();
    const rate = $(".input_per").val();

    $('.wish_update').on('click', function(){
        $('.input_pd').removeAttr("readonly");
        $('.input_cost').removeAttr("readonly");
        $('.input_per').removeAttr("readonly");
    });

    /* 저장 버튼 */
    $("#btn-wishSave").click(function() {
        wishListSave();
        var str = "";
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
                  wishList: $("#input_list").val(),
                  price: $("#input_price").val(),
                  rate: $("#input_rate").val()
            },
            success: function(data) {
                alert("성공");
                $(this).submit();

            },
            error: function() {
                console.log(data);
                alert("실패");
            }

        }); // ajax end.
         return false;
    };


});
