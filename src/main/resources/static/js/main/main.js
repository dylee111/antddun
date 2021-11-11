$(document).ready(function() {
    $('.btn_login').on('click', function(){
        $('#unlogin').hide();
        $('.wish').show();
        $('.container-main').css("margin-top", '13rem');
        $('#login').show();
    });

    <!--WishList Close JS-->
    $('.wish_close').on('click', function(){
        $('.wish').hide();
        $('.container-main').css("margin-top", '9rem');
    });

    <!--Alert Open JS-->
    $('.li_left').on('click', function(){
        $('#user_info').css("border-radius", '15px 15px 0 0');
        $('.li_left').css("border-radius", '0px');
        $('.li_right').css("border-radius", '0px');
        $('.li_left').css("background", '#f9c74f');
        $('.li_center').css("background", '#e3e3e3');
        $('.li_right').css("background", '#e3e3e3');
        $('#user_alert').show();
        $('#user_secret').hide();
    });

    $('.li_center').on('click', function(){
        $('#user_info').css("border-radius", '15px 15px 0 0');
        $('.li_left').css("border-radius", '0px');
        $('.li_right').css("border-radius", '0px');
        $('.li_center').css("background", '#f9c74f');
        $('.li_left').css("background", '#e3e3e3');
        $('.li_right').css("background", '#e3e3e3');
        $('#user_alert').show();
        $('#user_secret').hide();
    });

    $('.li_right').on('click', function(){
        $('#user_info').css("border-radius", '15px 15px 0 0');
        $('.li_left').css("border-radius", '0px');
        $('.li_right').css("border-radius", '0px');
        $('.li_right').css("background", '#f9c74f');
        $('.li_left').css("background", '#e3e3e3');
        $('.li_center').css("background", '#e3e3e3');
        $('#user_secret').show();
        $('#user_alert').hide();
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