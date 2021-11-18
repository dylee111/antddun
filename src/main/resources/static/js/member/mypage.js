//Tab 전환 효과
$(document).ready(function() {
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

    const wishList = new Array();
    const price = new Array();
    const rate = new Array();

    $(".save_btn").click(function() {
        $("input[name=wishList]").each(function(index, wish) {
            wishList.push($(wish).val());
        });
        $("input[name=price]").each(function(index, p) {
            price.push($(p).val());
        });
        $("input[name=rate]").each(function(index, r) {
            rate.push($(r).val());
        });
        console.log(wishList);
        console.log(price);
        console.log(rate);
    });

});

// 실수령 계산기
$(document).ready(function() {
    var earn = parseInt($('#earn').text());
    var monthly = parseInt((earn * 10000 / 12) - (earn * 10000  * 0.009));
    var result = monthly.toLocaleString();
    var result2 = "회원정보에서 '연봉'을 입력하세요."

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
