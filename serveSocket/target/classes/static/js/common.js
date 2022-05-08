$(function () {
  $(document).ready(function () {
    "use strict";
    function navScroll(){
        var window_top = $(window).scrollTop();
        var div_top = $('body').offset().top;
        if (window_top > div_top) {
                $('.header').addClass('header--sticky');
                $('.header__menu ul ul').addClass('submenu-header-sticky');
            } else {
                $('.header').removeClass('header--sticky');
                $('.header__menu ul ul').removeClass('submenu-header-sticky');
            }
    }
    $(window).scroll(function() {
        navScroll();
    });
    navScroll();
  });

  $('[data-toggle="tooltip"]').tooltip();
})
