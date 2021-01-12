var vaildator = window.vaildator || {};
$.extend(vaildator, {
    index: function() {
        var phone = null;
        var userName;
        vaildator.userInfo();
        vaildator.dateCheck();
    },
    userInfo: function() {
        var that = this;
        phone = $.cookie('Pflage');
        userFlag = $.cookie('userFlag');
        if (userFlag == 'true') {
            $.ajax({
                url: '/selectByphone',
                type: 'GET',
                data: {
                    'phone': phone,
                },
                success: function(data) {
                    var responses = data;
                    var msgCode = responses.resultId;
                    if (msgCode == 'N001') {
                        $.cookie = ("Pflage", null);
                        userName = responses.userInfoList[0].nickname;
                        var userLevel = responses.userInfoList[0].level;

                        $('.navbar').find('.user-name').text(userName);
                        $('.navbar').find('.user-level').text(userLevel);
                    } else {
                        window.location.replace("login.html");
                    }
                },
                error: function(e) {
                    console.log(e.status);
                    console.log(e.responseText);
                }
            })
        } else {
            window.location.replace("login.html");
        }
    },
    dateCheck: function() {
        laydate.render({
            elem: '#startTime',
            type: 'datetime',
            min: 'nowTime'
        })
    }
});

$(function() {
    vaildator.index();
})