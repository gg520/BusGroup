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



    //初次加载可用站点

    $('div.content.table-responsive.table-full-width').empty();
    var page=1;
    var body={startNum:page,num:10};
    $('input[type=hidden]').attr("value",page);

    $.ajax({
        type: 'POST',
        url: '/web/search/searchusingstation',
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
                var thead="<table class='table table-striped'>\n" +
                    "<thead>\n" +
                    "<th>站点编号</th>\n" +
                    "<th>站点名称</th>\n" +
                    "<th>站点坐标</th>\n" +
                    "<th>站点状态</th>\n" +
                    "<th>操作</th>\n" +
                    "</thead>" +
                    "<tbody>" +
                    "</tbody>" +
                    "</table>";
                $('div.content.table-responsive.table-full-width').html(thead);
                var tbody="";
                var stationStatusStr="";
                $(res.data).each(function (index,item) {
                    if(item.stationStatus==0)
                         stationStatusStr="可用";
                    tbody+="<tr>"+
                        "<td>"+item.stationId+"</td>" +
                        "<td>"+item.stationName+"</td>" +
                        "<td>"+item.stationCoord+"</td>" +
                        "<td>"+stationStatusStr+"</td>" +
                        "<td><button class='btn btn-primary'>停用</button></td>" +
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



    //flag_0点击 再次点击可用站点
    $('li.flag_0').click(function () {
        $('div.content.table-responsive.table-full-width').empty();

        $('li.flag_0').attr("class","active flag_0");
        $('li.flag_1').attr("class","flag_1");


        var page=1;
        var body={startNum:page,num:10};
        $('input[type=hidden]').attr("value",page);

        $.ajax({
            type: 'POST',
            url: '/web/search/searchusingstation',
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
                    var thead="<table class='table table-striped'>\n" +
                        "<thead>\n" +
                        "<th>站点编号</th>\n" +
                        "<th>站点名称</th>\n" +
                        "<th>站点坐标</th>\n" +
                        "<th>站点状态</th>\n" +
                        "<th>操作</th>\n" +
                        "</thead>" +
                        "<tbody>" +
                        "</tbody>" +
                        "</table>";
                    $('div.content.table-responsive.table-full-width').html(thead);
                    var tbody="";
                    var stationStatusStr="";
                    $(res.data).each(function (index,item) {
                        if(item.stationStatus==0)
                            stationStatusStr="可用";
                        tbody+="<tr>"+
                            "<td>"+item.stationId+"</td>" +
                            "<td>"+item.stationName+"</td>" +
                            "<td>"+item.stationCoord+"</td>" +
                            "<td>"+stationStatusStr+"</td>" +
                            "<td><button class='btn btn-primary'>停用</button></td>" +
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


    //可用站点加载更多  flag=0
    $(document).on("click","div.panel.panel-success.more-flag0",function () {
        var page=$('input[type=hidden]').val();
        page=parseInt(page,10)+1;
        var body={startNum:page,num:10};
        $('input[type=hidden]').attr("value",page);
        $.ajax({
            type:'POST',
            url:'/web/search/searchusingstation',
            contentType:"application/json;charset=utf-8",
            dataType:"json",
            data:JSON.stringify(body),
            error:function () {
                alert("加载失败，请刷新重试!");
            },
            success:function (res) {
                var tbody="";
                var stationStatusStr="";
                $(res.data).each(function (index,item) {
                    if(item.stationStatus==0)
                        stationStatusStr="可用";
                    tbody+="<tr>"+
                        "<td>"+item.stationId+"</td>" +
                        "<td>"+item.stationName+"</td>" +
                        "<td>"+item.stationCoord+"</td>" +
                        "<td>"+stationStatusStr+"</td>" +
                        "<td><button class='btn btn-primary'>停用</button></td>" +
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

//  ---------------------------------------------以下为已经停用的站点---------------------------------------------

    //flag_1点击 加载停用站点
    $('li.flag_1').click(function () {
        $('div.content.table-responsive.table-full-width').empty();

        $('li.flag_0').attr("class","flag_0");
        $('li.flag_1').attr("class","active  flag_1");


        var page=1;
        var body={startNum:page,num:10};
        $('input[type=hidden]').attr("value",page);

        $.ajax({
            type: 'POST',
            url: '/web/search/searchstopstation',
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
                    var more="<div class='panel panel-success more-flag1'>"+
                        "<div class='panel-heading'>" +
                        "<center>" +
                        "<h3 class='panel-title'>点击加载更多数据</h3>" +
                        "</center>" +
                        "</div>" +
                        "</div>";
                    var thead="<table class='table table-striped'>\n" +
                        "<thead>\n" +
                        "<th>站点编号</th>\n" +
                        "<th>站点名称</th>\n" +
                        "<th>站点坐标</th>\n" +
                        "<th>站点状态</th>\n" +
                        "<th>操作</th>\n" +
                        "</thead>" +
                        "<tbody>" +
                        "</tbody>" +
                        "</table>";
                    $('div.content.table-responsive.table-full-width').html(thead);
                    var tbody="";
                    var stationStatusStr="";
                    $(res.data).each(function (index,item) {
                        if(item.stationStatus==0)
                            stationStatusStr="可用";
                        tbody+="<tr>"+
                            "<td>"+item.stationId+"</td>" +
                            "<td>"+item.stationName+"</td>" +
                            "<td>"+item.stationCoord+"</td>" +
                            "<td>"+stationStatusStr+"</td>" +
                            "<td><button class='btn btn-primary'>停用</button></td>" +
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


    //可用站点加载更多  flag=0
    $(document).on("click","div.panel.panel-success.more-flag1",function () {
        var page=$('input[type=hidden]').val();
        page=parseInt(page,10)+1;
        var body={startNum:page,num:10};
        $('input[type=hidden]').attr("value",page);
        $.ajax({
            type:'POST',
            url:'/web/search/searchstopstation',
            contentType:"application/json;charset=utf-8",
            dataType:"json",
            data:JSON.stringify(body),
            error:function () {
                alert("加载失败，请刷新重试!");
            },
            success:function (res) {
                var tbody="";
                var stationStatusStr="";
                $(res.data).each(function (index,item) {
                    if(item.stationStatus==0)
                        stationStatusStr="可用";
                    tbody+="<tr>"+
                        "<td>"+item.stationId+"</td>" +
                        "<td>"+item.stationName+"</td>" +
                        "<td>"+item.stationCoord+"</td>" +
                        "<td>"+stationStatusStr+"</td>" +
                        "<td><button class='btn btn-primary'>停用</button></td>" +
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

})