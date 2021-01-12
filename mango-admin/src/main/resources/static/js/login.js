$(function() {
    // Ajax button click
    var userFlag = false;
    $('#login').on('click', function() {
        $.ajax({
            url: '/login',
            type: 'POST',
            data: {
                'phone': $('#userName').val(),
                'password': $('#passWord').val()
            },
            success: function(data) {
                var responses = data;
                var msgCode = responses.resultId;
                if (msgCode == 'N001') {
                    userFlag = true;
                    $.cookie("userFlag", userFlag, { path: '/' });
                    $.cookie("Pflage", $('#userName').val(), { path: '/' });
                    window.location.replace("home.html");
                } else {
                    var msg = responses.resultContent;
                    if ($('.login-content').find('.errorCode').length > 0) {
                        $('.login-content').find('.errorCode').remove();
                    }
                    $('.login-content').find('.login-title').after('<p class="errorCode">' + msg + '</p>');
                }
            },
            error: function(e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        })
    });
    $('#register-btn').on('click', function() {
        if ($('#register-password').val() != $('#register-password2').val()) {
            $('#register').find('.modal-title').append('<small class="errorCode">两次密码不一致，请重新设置密码。</small>');
        } else {
            $.ajax({
                url: '/addUser',
                type: 'POST',
                data: {
                    'nickname': $('#register-name').val(),
                    'password': $('#register-password').val(),
                    'phone': $('#register-phone').val()
                },
                success: function(data) {
                    var responses = data;
                    var msgCode = responses.resultId;
                    if (msgCode == 'N001') {
                        if ($('#register').find('.errorCode').length > 0) {
                            $('#register').find('.errorCode').remove();
                        }
                        $('#register').find('.modal-title').append('<small class="errorCode">注册成功，请登录。</small>');
                        setTimeout(function() {
                            window.location.href = "login.html";
                        }, 1000);

                    } else {
                        var msg = responses.resultContent;
                        if ($('#register').find('.errorCode').length > 0) {
                            $('#register').find('.errorCode').remove();
                        }
                        $('#register').find('.modal-title').append('<small class="errorCode">' + msg + '</small>');
                    }

                    //window.location.replace("login.html");
                },
                error: function(e) {
                    console.log(e.status);
                    console.log(e.responseText);
                }
            })
        }
    });
});