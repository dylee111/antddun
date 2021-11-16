$(document).ready(function() {
    <!--WishList Close JS-->
    $('.wish_close').on('click', function(){
        if(!window.localStorage){
            $('.wish').hide();
            $('.blank').hide();
        }
    });

    <!-- Hourly Wage JS -->
//    function getWage(){
//        var startTime
//        var endTime
//        var now = new Date();
//        var hourlyWage =
//    }

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

//    i =
//    hourlyWage = 0;
//    for(i=0; i>=; i++) {
//        hourlyWage +=hourlyWage;
//    }

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