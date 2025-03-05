$(function() {
    function openLoginForm() {
        $("#login-form").delay(100).fadeIn(100);
        $("#register-form").fadeOut(100);
        $('#register-form-link').removeClass('active');
        $('#login-form-link').addClass('active');
    }
    function openRegisterForm(){
        $("#register-form").delay(100).fadeIn(100);
        $("#login-form").fadeOut(100);
        $('#login-form-link').removeClass('active');
        $('#register-form-link').addClass('active');
    }
    if(window.location.href.indexOf("register") > -1) {
        openRegisterForm();
        $("#register-form").css('display', 'block');
        $("#login-form").css('display', 'none');
    }
    else{
        openLoginForm();
        $("#register-form").css('display', 'none');
        $("#login-form").css('display', 'block');

    }

    $('#login-form-link').click(function(e) {
        openLoginForm();
        e.preventDefault();
    });
    $('#register-form-link').click(function(e) {
        openRegisterForm();
        e.preventDefault();
    });
});
