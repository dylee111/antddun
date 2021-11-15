$(document).ready(function() {
    <!-- Search Bar JS -->
    $.fn.toggleState = function (b) {
    	$(this)
    		.stop()
    		.animate(
    			{
    				width: b ? "200px" : "50px"
    			},
    			600,
    			"easeOutElastic"
    		);
    };

    $(document).ready(function () {
    	var container = $(".container");
    	var boxContainer = $(".search-box-container");
    	var submit = $(".submit");
    	var searchBox = $(".search-box");
    	var response = $(".response");
    	var isOpen = false;
    	submit.on("mousedown", function (e) {
    		e.preventDefault();
    		boxContainer.toggleState(!isOpen);
    		isOpen = !isOpen;
    		if (!isOpen) {
    			handleRequest();
    		} else {
    			searchBox.focus();
    		}
    	});
    	searchBox.keypress(function (e) {
    		if (e.which === 13) {
    			boxContainer.toggleState(false);
    			isOpen = false;
    			handleRequest();
    		}
    	});
    	searchBox.blur(function () {
    		boxContainer.toggleState(false);
    		isOpen = false;
    	});
    	function handleRequest() {
    		// You could do an ajax request here...
    		var value = searchBox.val();
    		searchBox.val("");
    		if (value.length > 0) {
    			response.text('Searching for "' + value + '" . . .');
    			response
    				.animate(
    					{
    						opacity: 1
    					},
    					300
    				)
    				.delay(2000)
    				.animate(
    					{
    						opacity: 0
    					},
    					300
    				);
    		}
    	}
    });

    <!--WishList Close JS-->
    $('.wish_close').on('click', function(){
        $('.wish').hide();
        $('.blank').hide();
    });

    <!--Alert Open JS-->
    $('.li_left').on('click', function(){
        $('#user_alert').toggle(
            function(){
                $('#user_alert').show();
                $('#user_info').css("border-radius", '15px 15px 0 0');
                $('.li_left').css("border-radius", '0px');
                $('.li_right').css("border-radius", '0px');
                $('.li_left').css("background", '#f9c74f');
                $('.li_center').css("background", '#e3e3e3');
                $('.li_right').css("background", '#e3e3e3');
            },
            function(){
                $('#user_alert').hide();
                $('#user_info').css("border-radius", '15px');
                $('.li_left').css("border-radius", '0 0 0 10px');
                $('.li_right').css("border-radius", '0 0 10px 0');
                $('.li_left').css("background", '#f9c74f');
                $('.li_center').css("background", '#f9c74f');
                $('.li_right').css("background", '#f9c74f');
            }
        );
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