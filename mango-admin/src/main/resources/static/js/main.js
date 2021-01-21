var home = window.home || {};
$.extend(home, {
    index: function() {
        var phone = null;
        var userName;
        home.userInfo();
        home.selectCampaign();
        home.createCampaign();
        home.selectPickers();
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
    createCampaign: function() {
        $('#createCampaign').click(function() {
            $.ajax({
                url: '/insertStableInfo',
                type: 'POST',
                data: {
                    'idname': $('#creatCamapignFunction').find('#recipient-name').val(),
                    'memo': $('#creatCamapignFunction').find('#message-text').val(),
                    'peoplenum': $('#creatCamapignFunction').find('#group-number').val(),
                    'starttime': $('#creatCamapignFunction').find('#startTime').val(),
                    'register': userName
                },
                success: function(data) {
                    var responses = data;
                    var msgCode = responses.resultId;
                    var msgInfo = responses.resultContent;
                    if (msgCode == 'N001') {
                        home.selectCampaign();
                        window.location.replace("campaign.html");
                    } else {
                        if ($('#creatCampaign').find('.errorCode').length > 0) {
                            $('#creatCampaign').find('.errorCode').remove();
                        }
                        $('#creatCampaign').find('.modal-body').append('<small class="errorCode">' + msgInfo + '</small>');
                    }
                },
                error: function(e) {
                    $('#warning-msg').find('strong').text('创建失败，再试一次！').show();
                    console.log(e.status);
                    console.log(e.responseText);
                }
            })
        })
    },
    selectCampaign: function() {
        $.ajax({
            url: '/selectAllStableInfo',
            type: 'GET',
            data: {},
            success: function(data) {
                var responses = data;
                var msgCode = responses.resultId;
                if (msgCode == 'N001') {
                    var campaignList = responses.list;
                    if (campaignList.length > 0) {
                        var campaignTableHtml = '';
                        for (var campaignInfo in campaignList) {
                            campaignTableHtml += '<div class="col mb-6"><div class="card"><div class="card-body"><h5 class="card-title">' + campaignList[campaignInfo].idname + '</h5></p><a href="./campaign.html" class="btn btn-outline-info">我要报名</a></div></div></div>';
                        }
                        if ($('#campaignTable').find('.mb-6').length > 0) {
                            $('#campaignTable').find('.mb-6').remove();
                        }
                        $('#campaignTable').append(campaignTableHtml);
                        $('#creatCampaign').find('.close').trigger("click");
                    }

                } else {
                    $('#warning-msg').find('strong').text('').text('检索失败，再刷新试试！').show();
                }
            },
            error: function(e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        })
    },

    selectPickers: function() {
    //活动名称选择
        $.ajax({
            url: '/getAllIdName',
            type: 'GET',
            data: {},
            success: function(data) {
                var responses = data;
                var msgCode = responses.resultId;
                if (msgCode == 'N001') {
                    var pickersList = responses.list;
                    if (pickersList.length > 0) {
                        var pickersTable = '';
                        for (var pickersInfo in pickersList) {
                            pickersTable += '<a class="dropdown-item" onClick="changeAction(\'' + pickersList[pickersInfo] + '\')" href="#">' + pickersList[pickersInfo] + '</a>';
                        }
                        $('#selectPickers').append(pickersTable);
                    }
                } else {
                    $('#warning-msg').find('strong').text('').text('检索失败，再刷新试试！').show();
                }
            },
            error: function(e) {
                console.log(e.status);
                console.log(e.responseText);
            }
        })
    }
   
});

function changeAction(obj){
	//传过来的参数赋给活动名称的text文本	
	$("#recipient-name").val(obj);
}

$(function() {
    home.index();
})