$(document).ready(function () {

    //没有更多数据
    var noneleft="<div class='panel panel-success noneleft'>" +
        "<div class='panel-heading'>" +
        "<center>" +
        "<h3 class='panel-title'>没有更多数据</h3>" +
        "</center>" +
        "</div>" +
        "</div>";
    //没有数据
    var nothing="<div class='panel panel-success nothing'>" +
        "<div class='panel-heading'>" +
        "<center>" +
        "<h3 class='panel-title'>没有数据</h3>" +
        "</center>" +
        "</div>" +
        "</div>";



    //初次加载 已经审核通过的线路

    $('div.content.table-responsive.table-full-width').empty();
    var page=1;
    var body={startNum:page,num:10};
    $('input[type=hidden]').attr("value",page);

    $.ajax({
        type: 'POST',
        url: '/web/search/runcheckroute',
        contentType: "application/json;charset=utf-8",
        dataType: "json",
        data: JSON.stringify(body),
        error: function () {
            alert("加载失败，请刷新重试!");
        },
        success: function (res) {
            if(res.data.length<=0){
                $('div.content.table-responsive.table-full-width').html(nothing);
            }else {
                var more="<div class='panel panel-success more-flag0'>"+
                    "<div class='panel-heading'>" +
                    "<center>" +
                    "<h3 class='panel-title'>点击加载更多数据</h3>" +
                    "</center>" +
                    "</div>" +
                    "</div>";
                var theader="<table class='table table-striped'>" +
                    "<thead>" +
                    "<th>线路编号</th>" +
                    "<th>创建人</th>" +
                    "<th>创建时间</th>" +
                    "<th>起点站</th>" +
                    "<th>终点站</th>" +
                    "<th>出发时间</th>" +
                    "<th>预计到达</th>" +
                    "<th>运行周期</th>" +
                    " <th>审核操作</th>"+
                    "</thead>" +
                    "<tbody></tbody>" +
                    "</table>";
                $('div.content.table-responsive.table-full-width').html(theader);
                var tbody="";
                $(res.data).each(function (index,item) {

                    tbody+="<tr>"+
                        "<td>"+item.routeId+"</td>" +
                        "<td>"+item.creatUser+"</td>" +
                        "<td>"+item.creatTime+"</td>" +
                        "<td>"+item.startSite+"</td>" +
                        "<td>"+item.endSite+"</td>" +
                        "<td>"+item.startTime+"</td>" +
                        "<td>"+item.endTime+"</td>" +
                        "<td>"+item.runTime+"</td>" +
                        "<td><a class='btn exam-p' th:data-routeId='${route.routeId}'>通过</a><a class='btn exam-n' th:data-routeId='${route.routeId}'>不通过</a></td>" +
                        "</tr>";
                })
                $('tbody').append(tbody);
                if(res.data.length<10){
                    $('div.content.table-responsive.table-full-width').append(noneleft);
                }else{
                    $('div.content.table-responsive.table-full-width').append(more);
                }
            }
        }

    });



    //flag_0点击 再次点击已经通过的线路
    $('li.flag_0').click(function () {
        $('div.content.table-responsive.table-full-width').empty();

        $('li.flag_0').attr("class","active flag_0");
        $('li.flag_1').attr("class","flag_1");
        $('li.flag_2').attr("class","flag_2");


        var page=1;
        var body={startNum:page,num:10};
        $('input[type=hidden]').attr("value",page);

        $.ajax({
            type: 'POST',
            url: '/web/search/runcheckroute',
            contentType: "application/json;charset=utf-8",
            dataType: "json",
            data: JSON.stringify(body),
            error: function () {
                alert("加载失败，请刷新重试!");
            },
            success: function (res) {
                if(res.data.length<=0){
                    $('div.content.table-responsive.table-full-width').html(nothing);
                }else {
                    var more="<div class='panel panel-success more-flag0'>"+
                        "<div class='panel-heading'>" +
                        "<center>" +
                        "<h3 class='panel-title'>点击加载更多数据</h3>" +
                        "</center>" +
                        "</div>" +
                        "</div>";
                    var theader="<table class='table table-striped'>" +
                        "<thead>" +
                        "<th>线路编号</th>" +
                        "<th>创建人</th>" +
                        "<th>创建时间</th>" +
                        "<th>起点站</th>" +
                        "<th>终点站</th>" +
                        "<th>出发时间</th>" +
                        "<th>预计到达</th>" +
                        "<th>运行周期</th>" +
                        " <th>审核操作</th>"+
                        "</thead>" +
                        "<tbody></tbody>" +
                        "</table>";
                    $('div.content.table-responsive.table-full-width').html(theader);
                    var tbody="";
                    $(res.data).each(function (index,item) {

                        tbody+="<tr>"+
                            "<td>"+item.routeId+"</td>" +
                            "<td>"+item.creatUser+"</td>" +
                            "<td>"+item.creatTime+"</td>" +
                            "<td>"+item.startSite+"</td>" +
                            "<td>"+item.endSite+"</td>" +
                            "<td>"+item.startTime+"</td>" +
                            "<td>"+item.endTime+"</td>" +
                            "<td>"+item.runTime+"</td>" +
                            "<td><a class='btn exam-p' th:data-routeId='${route.routeId}'>通过</a><a class='btn exam-n' th:data-routeId='${route.routeId}'>不通过</a></td>" +
                            "</tr>";
                    })
                    $('tbody').append(tbody);
                    if(res.data.length<10){
                        $('div.content.table-responsive.table-full-width').append(noneleft);
                    }else{
                        $('div.content.table-responsive.table-full-width').append(more);
                    }
                }
            }

        });
    });


    //已经通过线路加载更多  flag=0
    $(document).on("click","div.panel.panel-success.more-flag0",function () {
        var page=$('input[type=hidden]').val();
        page=parseInt(page,10)+1;
        var body={startNum:page,num:10};
        $('input[type=hidden]').attr("value",page);
        $.ajax({
            type:'POST',
            url:'/web/search/runcheckroute',
            contentType:"application/json;charset=utf-8",
            dataType:"json",
            data:JSON.stringify(body),
            error:function () {
                alert("加载失败，请刷新重试!");
            },
            success:function (res) {
                var tbody="";
                $(res.data).each(function (index,item) {
                    tbody+="<tr>" +
                        "<td>" + item.routeId + "</td>" +
                        "<td>" + item.creatUser + "</td>" +
                        "<td>" + item.creatTime + "</td>" +
                        "<td>" + item.startSite + "</td>" +
                        "<td>" + item.endSite + "</td>" +
                        "<td>" + item.startTime + "</td>" +
                        "<td>" + item.endTime + "</td>" +
                        "<td>" + item.runTime + "</td>" +
                        "<td><a class='btn exam-p' th:data-routeId='${route.routeId}'>通过</a><a class='btn exam-n' th:data-routeId='${route.routeId}'>不通过</a></td>" +
                        "</tr>";

                })
                $('tbody').append(tbody);
                if(res.data.length<10){
                    $('div.content.table-responsive.table-full-width div:last-child').remove();
                    $('div.content.table-responsive.table-full-width').append(noneleft);
                }

            }

        });

    });

    //--------------------------以下为运行中-----------------------------

    //flag_1点击 点击运行中的线路
    $('li.flag_1').click(function () {
        $('div.content.table-responsive.table-full-width').empty();
        $('li.flag_1').attr("class","active flag_1");
        $('li.flag_0').attr("class","flag_0");
        $('li.flag_2').attr("class","flag_2");

        var page=1;
        var body={startNum:page,num:10};
        $('input[type=hidden]').attr("value",page);
        $.ajax({
            type:'POST',
            url:'/web/search/runingroute',
            contentType:"application/json;charset=utf-8",
            dataType:"json",
            data:JSON.stringify(body),
            error:function () {
                alert("加载失败，请刷新重试!");
            },
            success:function (res) {
                if(res.data.length<=0){
                    $('div.content.table-responsive.table-full-width').html(nothing);
                }else {
                    var more="<div class='panel panel-success more-flag1'>"+
                        "<div class='panel-heading'>" +
                        "<center>" +
                        "<h3 class='panel-title'>点击加载更多数据</h3>" +
                        "</center>" +
                        "</div>" +
                        "</div>";
                    var theader="<table class='table table-striped'>" +
                        "<thead>" +
                        "<th>线路编号</th>" +
                        "<th>创建人</th>" +
                        "<th>创建时间</th>" +
                        "<th>起点站</th>" +
                        "<th>终点站</th>" +
                        "<th>出发时间</th>" +
                        "<th>预计到达</th>" +
                        "<th>运行周期</th>" +
                        "</thead>" +
                        "<tbody></tbody>" +
                        "</table>";
                    var tbody="";
                    $('div.content.table-responsive.table-full-width').html(theader);
                    $(res.data).each(function (index,item) {
                        tbody+="<tr>" +
                            "<td>"+item.routeId+"</td>" +
                            "<td>"+item.creatUser+"</td>" +
                            "<td>"+item.creatTime+"</td>" +
                            "<td>"+item.startSite+"</td>" +
                            "<td>"+item.endSite+"</td>" +
                            "<td>"+item.startTime+"</td>" +
                            "<td>"+item.endTime+"</td>" +
                            "<td>"+item.runTime+"</td>" +
                            "</tr>";
                    })
                    $('table.table.table-striped').append(tbody);
                    if(res.data.length<10){
                        $('div.content.table-responsive.table-full-width').append(noneleft);
                    }else{
                        $('div.content.table-responsive.table-full-width').append(more);
                    }
                }
            }

        })


    });

    //flag1  运行中线路加载更多
    $(document).on("click","div.panel.panel-success.more-flag1",function () {
        var page=$('input[type=hidden]').val();
        page=parseInt(page,10)+1;
        var body={startNum:page,num:10};
        $('input[type=hidden]').attr("value",page);
        $.ajax({
            type:'POST',
            url:'/web/search/runingroute',
            contentType:"application/json;charset=utf-8",
            dataType:"json",
            data:JSON.stringify(body),
            error:function () {
                alert("加载失败，请刷新重试!");
            },
            success:function (res) {
                var tbody="";
                $(res.data).each(function (index, item) {
                    tbody+= "<tr>" +
                        "<td>" + item.routeId + "</td>" +
                        "<td>" + item.creatUser + "</td>" +
                        "<td>" + item.creatTime + "</td>" +
                        "<td>" + item.startSite + "</td>" +
                        "<td>" + item.endSite + "</td>" +
                        "<td>" + item.startTime + "</td>" +
                        "<td>" + item.endTime + "</td>" +
                        "<td>" + item.runTime + "</td>" +
                        "</tr>";
                })
                $('table.table.table-striped').append(tbody);
                if(res.data.length<10){
                    $('div.content.table-responsive.table-full-width div:last-child').remove();
                    $('div.content.table-responsive.table-full-width').append(noneleft);
                }
            }
        });

    })
    //--------------------------以下为已经过期-----------------------------
    //flag_2点击 点击已经过期的线路
    $('li.flag_2').click(function () {
        $('div.content.table-responsive.table-full-width').empty();
        $('li.flag_2').attr("class","active flag_2");
        $('li.flag_0').attr("class","flag_0");
        $('li.flag_1').attr("class","flag_1");

        var page=1;
        var body={startNum:page,num:10};
        $('input[type=hidden]').attr("value",page);
        $.ajax({
            type:'POST',
            url:'/web/search/overdueroute',
            contentType:"application/json;charset=utf-8",
            dataType:"json",
            data:JSON.stringify(body),
            error:function () {
                alert("加载失败，请刷新重试!");
            },
            success:function (res) {
                //如果没有返回数据
                if(res.data.length<=0){
                    $('div.content.table-responsive.table-full-width').html(nothing);
                }else {
                    var more="<div class='panel panel-success more-flag2'>"+
                        "<div class='panel-heading'>" +
                        "<center>" +
                        "<h3 id='moreWait' class='panel-title'>点击加载更多数据</h3>" +
                        "</center>" +
                        "</div>" +
                        "</div>";

                    var theader="<table class='table table-striped'>" +
                        "<thead>" +
                        "<th>线路编号</th>" +
                        "<th>创建人</th>" +
                        "<th>创建时间</th>" +
                        "<th>起点站</th>" +
                        "<th>终点站</th>" +
                        "<th>出发时间</th>" +
                        "<th>预计到达</th>" +
                        "<th>运行周期</th>" +
                        "</thead>" +
                        "<tbody></tbody>" +
                        "</table>";
                    var tbody="";
                    $('div.content.table-responsive.table-full-width').html(theader);
                    $(res.data).each(function (index,item) {
                        tbody+="<tr>" +
                            "<td>"+item.routeId+"</td>" +
                            "<td>"+item.creatUser+"</td>" +
                            "<td>"+item.creatTime+"</td>" +
                            "<td>"+item.startSite+"</td>" +
                            "<td>"+item.endSite+"</td>" +
                            "<td>"+item.startTime+"</td>" +
                            "<td>"+item.endTime+"</td>" +
                            "<td>"+item.runTime+"</td>" +
                            "</tr>";

                    })
                    $('table.table.table-striped').append(tbody);
                    if(res.data.length<10){
                        $('div.content.table-responsive.table-full-width').append(noneleft);
                    }else {
                        $('div.content.table-responsive.table-full-width').append(more);
                    }
                }
            }


        })
    });

    //flag2已经过期线路加载更多
    $(document).on("click","div.panel.panel-success.more-flag2",function () {
        var page=$('input[type=hidden]').val();
        page=parseInt(page,10)+1;
        var body={startNum:page,num:10};
        $('input[type=hidden]').attr("value",page);
        $.ajax({
            type:'POST',
            url:'/web/search/overdueroute',
            contentType:"application/json;charset=utf-8",
            dataType:"json",
            data:JSON.stringify(body),
            error:function () {
                alert("加载失败，请刷新重试!");
            },
            success:function (res) {
                var tbody="";
                $(res.data).each(function (index,item) {
                    tbody+="<tr>" +
                        "<td>"+item.routeId+"</td>" +
                        "<td>"+item.creatUser+"</td>" +
                        "<td>"+item.creatTime+"</td>" +
                        "<td>"+item.startSite+"</td>" +
                        "<td>"+item.endSite+"</td>" +
                        "<td>"+item.startTime+"</td>" +
                        "<td>"+item.endTime+"</td>" +
                        "<td>"+item.runTime+"</td>" +
                        "</tr>";
                })
                $('table.table.table-striped').append(tbody);
                if(res.data.length<10){
                    $('div.content.table-responsive.table-full-width div:last-child').remove();
                    $('div.content.table-responsive.table-full-width').append(noneleft);
                }
            }

        });

    })

})