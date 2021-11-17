
$(document).ready(function() {
    <!--WishList Close JS-->
//    function setCookie(name, value, expirehours) {
//        var todayDate = new Date();
//        todayDate.setHours( todayDate.getHours() + expirehours );
//        document.cookie = name + "=" +escape( value ) + "; path=/; expires=" + todayDate.toGMTString() + ";"
//    }
//
    $('.wish_close').on('click', function(){
//        setCookie( "ncookie","done",24);
        $('.wish').hide();
        $('.blank').hide();
    });

    <!-- Hourly Wage JS -->
    $(document).ready(function() {
        <!--현재 시간-->
        var nowTime = new Date().getHours();
        var hours = new Date().getHours()*60;
        var minutes = new Date().getMinutes();
        var now = hours + minutes;

        <!--출근 시간-->
        var startTime = $('#startTime').text();
        var startHour = parseInt(startTime.substring(0,2));
        var startHours = parseInt(startTime.substring(0,2))*60;
        var startMinutes = parseInt(startTime.substring(3,5));
        var start = startHours + startMinutes;

        <!--퇴근 시간-->
        var endTime = $('#endTime').text();
        var endHour = parseInt(endTime.substring(0,2));
        var endHours = parseInt(endTime.substring(0,2))*60;
        var endMinutes = parseInt(endTime.substring(3,5));
        var end = endHours + endMinutes;

        <!--시급 계산-->
        var salary = $('#salary').text();
        var earn = parseInt(salary) * 10000;
        var workTime = ((endHours - startHours) + (endMinutes - startMinutes)) / 60;
        var hourlyWage = Math.floor(((earn / 12) - (earn * 0.009)) / 20 / workTime);

        var nowWage = (nowTime - startHour) * hourlyWage;
        var reset = "근무시간이 아닙니다.";
        if( startHour-1 < nowTime < endHour+1) {
            var result = nowWage.toLocaleString();
            $('#daily_earn').text(result+" 원");
        } else {
            $('#daily_earn').text(reset);
        }
    });

//    let run = setInterval(function(){
//        var reset = "근무시간이 아닙니다.";
//
//        if (nowTime <= i++ < workTime) {
//            result = i*hourlyWage;
//            console.log(result);
//        } else {
//            clearInterval(run);
//            console.log(reset);
//        }
//    }, 1000*60*60);

//    let run = setInterval(function(){
//        var result = 0;
//        var reset = "---";
//
//        for(i=0; i<workTime; i++){
//            result = i*hourlyWage;
//            console.log(result);
//        }
//        if(now >= start && now <= end) {
//            result += hourlyWage;
//            //return $('#daily_earn').val(result);
//            console.log(result);
//        } else {
//            console.log(reset);
//            clearInterval(run);
//            return $('#daily_earn').val(reset);
//        }
//    }, 5000);


    <!--Alert Open JS-->
    $('.li_left').click(function(){
        if($('#user_alert').css("display") == "none") {
            $('#user_alert').show();
            $('#user_msg').hide();
            $('#user_secret').hide();
            $('#user_info').css("border-radius", '15px 15px 0 0');
            $('.li_left').css("border-radius", '0px');
            $('.li_right').css("border-radius", '0px');
            $('.li_left').css("background", '#f9c74f');
            $('.li_center').css("background", '#e3e3e3');
            $('.li_right').css("background", '#e3e3e3');
        } else {
            $('#user_alert').hide();
            $('#user_info').css("border-radius", '15px');
            $('.li_left').css("border-radius", '0 0 0 10px');
            $('.li_right').css("border-radius", '0 0 10px 0');
            $('.li_left').css("background", '#f9c74f');
            $('.li_center').css("background", '#f9c74f');
            $('.li_right').css("background", '#f9c74f');
        }
    });
    $('.li_center').click(function(){
        if($('#user_msg').css("display") == "none") {
            $('#user_msg').show();
            $('#user_secret').hide();
            $('#user_alert').hide();
            $('#user_info').css("border-radius", '15px 15px 0 0');
            $('.li_left').css("border-radius", '0px');
            $('.li_right').css("border-radius", '0px');
            $('.li_left').css("background", '#e3e3e3');
            $('.li_center').css("background", '#f9c74f');
            $('.li_right').css("background", '#e3e3e3');
        } else {
            $('#user_msg').hide();
            $('#user_info').css("border-radius", '15px');
            $('.li_left').css("border-radius", '0 0 0 10px');
            $('.li_right').css("border-radius", '0 0 10px 0');
            $('.li_left').css("background", '#f9c74f');
            $('.li_center').css("background", '#f9c74f');
            $('.li_right').css("background", '#f9c74f');
        }
    });
    $('.li_right').click(function(){
        if($('#user_secret').css("display") == "none") {
            $('#user_secret').show();
            $('#user_msg').hide();
            $('#user_alert').hide();
            $('#user_info').css("border-radius", '15px 15px 0 0');
            $('.li_left').css("border-radius", '0px');
            $('.li_right').css("border-radius", '0px');
            $('.li_left').css("background", '#e3e3e3');
            $('.li_center').css("background", '#e3e3e3');
            $('.li_right').css("background", '#f9c74f');
        } else {
            $('#user_secret').hide();
            $('#user_info').css("border-radius", '15px');
            $('.li_left').css("border-radius", '0 0 0 10px');
            $('.li_right').css("border-radius", '0 0 10px 0');
            $('.li_left').css("background", '#f9c74f');
            $('.li_center').css("background", '#f9c74f');
            $('.li_right').css("background", '#f9c74f');
        }
    });

    <!--slick slider JS-->
    $('.visual').slick({
      slidesToShow: 5,
      slidesToScroll: 5,
      pauseOnHover : true,
      prevArrow : "<button type='button' class='slick-prev'>Previous</button>",
      nextArrow : "<button type='button' class='slick-next'>Next</button>",
      draggable : true,
      responsive: [
        { breakpoint: 1400, settings: { slidesToShow: 4, slidesToScroll: 4 }},
        { breakpoint: 1200, settings: { slidesToShow: 3, slidesToScroll: 3 }},
        { breakpoint: 765, settings: { slidesToShow: 1, slidesToScroll: 1}}
      ]
    });

    var filtered = false;

    $('.js-filter').on('click', function(){
      if (filtered === false) {
        $('.visual').slick('slickFilter',':even');
        $(this).text('Unfilter Slides');
        filtered = true;
      } else {
        $('.visual').slick('slickUnfilter');
        $(this).text('Filter Slides');
        filtered = false;
      }
    });

    <!--Lunch slot JS-->
    $(document).ready(function() {
        var width = $(window).width();

        if (width > 991){
            $('.slotRun1').jSlots({spinner: '.playNormal1'});
        } else {
            $('.slotRun2').jSlots({spinner: '.playNormal2'});
        }
    });

    $(window).resize(function() {
        var width = $(window).width();

        if (width > 991){
            $('.slotRun1').jSlots({spinner: '.playNormal1'});
        } else {
            $('.slotRun2').jSlots({spinner: '.playNormal2'});
        }
    });
});