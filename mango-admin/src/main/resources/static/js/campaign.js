var campaign = window.campaign || {};
$.extend(campaign, {
    index: function() {
        var phone = null;
        var userName;
        campaign.userInfo();
        campaign.selectCampaignInfo();

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
    selectCampaignInfo: function() {
        //活动team名称选择
        var campaignName = null;
        campaignName = sessionStorage.getItem('campaignName');
        $(".campaign-banner").find("h1").text(campaignName);
        $.ajax({
            url: '/selectStableByIdName',
            type: 'GET',
            data: {
                'idName': campaignName
            },
            success: function(data) {
                var responses = data;
                var msgCode = responses.resultId;
                if (msgCode == 'N001') {
                    var campaignInfoList = responses.mainList;
                    if (campaignInfoList.length > 0) {
                        var applyed = false;
                        var groupHtml = '';
                        var currentMemberId = null;
                        if ($('#campaignTable').find('.mb-6').length > 0) {
                            $('#campaignTable').find('.mb-6').remove();
                        }
                        for (var campaignInfo = 0; campaignInfoList.length > campaignInfo; campaignInfo++) {
                            groupHtml += '<div class="col mb-6" teamNum = "' + campaignInfoList[campaignInfo].idnameteamnum + '"><div class="card campaign-card"><button type="button" class="close delete-campaign" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button><div class="card-body"><h5 class="card-title"> Team Id: ' + campaignInfoList[campaignInfo].idnameteamnum + '</h5> <h5 class="card-title">' + campaignInfoList[campaignInfo].memo + '</h5><p class="card-text"> 队长: ' + campaignInfoList[campaignInfo].register + '</p><p class="card-text start-time">开始时间: <span>' + campaignInfoList[campaignInfo].starttime + '</span></p><p class="card-text person-num">人数:<span> ' + campaignInfoList[campaignInfo].peoplenum + '</span></p><div class="member-info"><p>队友一览</p><ol>';
                            var memberList = campaignInfoList[campaignInfo].stables;
                            if (memberList.length > 0) {
                                for (var i = 0; memberList.length > i; i++) {
                                    var memberId = memberList[i].id;
                                    var memberName = memberList[i].nickname;
                                    var loginName = $('.navbar').find('.user-name').text();
                                    if (campaignInfoList[campaignInfo].register === loginName) {
                                        groupHtml += '<li class="' + memberId + '" onclick="deleteTeamMember(' + memberId + ');">' + memberName + '<button type="button" class="close delete-member" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button></li>';
                                    } else {
                                        groupHtml += '<li class="' + memberId + '>' + memberName + '</li>';
                                    }

                                    if (memberName === loginName) {
                                        applyed = true;
                                        currentMemberId = memberList[i].id;
                                    }
                                }
                                if (applyed == true) {
                                    groupHtml += '</ol></div><a href="javascript:void(0);" class="btn btn-outline-info apply-cancel disaplay" onclick="cancelCampaign(' + currentMemberId + ')">取消报名</a></div></div></div>';
                                } else {
                                    groupHtml += '</ol></div><a href="javascript:void(0);" onclick="applyCampaign(' + campaignInfoList[campaignInfo].idnameteamnum + ')" class="btn btn-outline-info apply-bottom">我要报名</a></div></div></div>';
                                }
                            }
                        }

                        $('#campaignTable').append(groupHtml);
                        $('.delete-campaign').click(function() {
                            $(this).parent('.mb-6').remove();
                        });
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
});
$(function() {
    campaign.index();
})