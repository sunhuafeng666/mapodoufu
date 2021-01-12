var campaign = window.campaign || {};
$.extend(campaign, {
    index: function() {
        var phone = null;
        var userName;
        campaign.userInfo();
        campaign.selectCampaignInfo();
        // campaign.applyCampaign();
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
    applyCampaign: function() {
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
    selectCampaignInfo: function() {
        $.ajax({
            url: '/selectStableByIdName',
            type: 'GET',
            data: {
                'idName':
            },
            success: function(data) {
                var responses = data;
                var msgCode = responses.resultId;
                if (msgCode == 'N001') {
                    var campaignInfoList = responses.mainList;
                    if (campaignInfoList.length > 0) {
                        var campaignTableHtml = '';
                        var memberInfoHtml = '';
                        for (var campaignInfo in campaignInfoList) {
                            campaignTableHtml += '<div class="col mb-6"><div class="card campaign-card"><button type="button" class="close delete-campaign" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><div class="card-body"> <h5 class="card-title">' + campaignList[campaignInfo].memo + '</h5><p class="card-text">' + campaignList[campaignInfo].register + '</p><p class="card-text start-time">开始时间: <span>' + campaignList[campaignInfo].starttime + '</span></p><p class="card-text person-num">人数:<span> ' + campaignList[campaignInfo].peoplenum + '</span></p><div class="member-info"><p>队友一览</p><ol>';
                            for (var memberInfo in campaignList[campaignInfo].nickname) {
                                memberInfoHtml = '<li>' + memberInfo + '<button type="button" class="close delete-member" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></li>'
                            }
                        }
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
$(function() {
    campaign.index();
})