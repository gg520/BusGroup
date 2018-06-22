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



    //初次加载 可用车辆

    $('div.content.table-responsive.table-full-width').empty();
    var page=1;
    var body={startNum:page,num:10};
    $('input[type=hidden]').attr("value",page);

    $.ajax({
        type: 'POST',
        url: '/web/search/findCanUseBus',
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
                var thead="<table class='table table-striped'>" +
                    "<thead>" +
                    "<th>车次</th>" +
                    "<th>车牌号</th>" +
                    "<th>车辆类型</th>" +
                    "<th>所有人</th>" +
                    "<th>使用性质</th>" +
                    "<th>品牌型号</th>" +
                    "<th>识别代号</th>" +
                    "<th>发动机号码</th>" +
                    "<th>核载人数</th>" +
                    "<th>操作</th>" +
                    "</thead>" +
                    "<tbody>" +
                    "</tbody>";
                $('div.content.table-responsive.table-full-width').html(thead);
                var tbody="";
                $(res.data).each(function (index,item) {

                    tbody+="<tr>"+
                        "<td>"+item.busNum+"</td>" +
                        "<td>"+item.busId+"</td>" +
                        "<td>"+item.busType+"</td>" +
                        "<td>"+item.busOwner+"</td>" +
                        "<td>"+item.characters+"</td>" +
                        "<td>"+item.model+"</td>" +
                        "<td>"+item.vin+"</td>" +
                        "<td>"+item.engineNum+"</td>" +
                        "<td>"+item.seating+"</td>" +
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



    //flag_0点击 再次点击可用车辆
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
            url: '/web/search/findCanUseBus',
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
                    var thead="<table class='table table-striped'>" +
                        "<thead>" +
                        "<th>车次</th>" +
                        "<th>车牌号</th>" +
                        "<th>车辆类型</th>" +
                        "<th>所有人</th>" +
                        "<th>使用性质</th>" +
                        "<th>品牌型号</th>" +
                        "<th>识别代号</th>" +
                        "<th>发动机号码</th>" +
                        "<th>核载人数</th>" +
                        "<th>操作</th>" +
                        "</thead>" +
                        "<tbody>" +
                        "</tbody>";
                    $('div.content.table-responsive.table-full-width').html(thead);
                    var tbody="";
                    $(res.data).each(function (index,item) {

                        tbody+="<tr>"+
                            "<td>"+item.busNum+"</td>" +
                            "<td>"+item.busId+"</td>" +
                            "<td>"+item.busType+"</td>" +
                            "<td>"+item.busOwner+"</td>" +
                            "<td>"+item.characters+"</td>" +
                            "<td>"+item.model+"</td>" +
                            "<td>"+item.vin+"</td>" +
                            "<td>"+item.engineNum+"</td>" +
                            "<td>"+item.seating+"</td>" +
                            "<button class='btn btn-primary'>停用</button>" +
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


    //可用车辆加载更多  flag=0
    $(document).on("click","div.panel.panel-success.more-flag0",function () {
        var page=$('input[type=hidden]').val();
        page=parseInt(page,10)+1;
        var body={startNum:page,num:10};
        $('input[type=hidden]').attr("value",page);
        $.ajax({
            type:'POST',
            url:'/web/search/waitcheckroute',
            contentType:"application/json;charset=utf-8",
            dataType:"json",
            data:JSON.stringify(body),
            error:function () {
                alert("加载失败，请刷新重试!");
            },
            success:function (res) {
                var tbody="";
                $(res.data).each(function (index,item) {
                    tbody+="<tr>"+
                        "<td>"+item.busNum+"</td>" +
                        "<td>"+item.busId+"</td>" +
                        "<td>"+item.busType+"</td>" +
                        "<td>"+item.busOwner+"</td>" +
                        "<td>"+item.characters+"</td>" +
                        "<td>"+item.model+"</td>" +
                        "<td>"+item.vin+"</td>" +
                        "<td>"+item.engineNum+"</td>" +
                        "<td>"+item.seating+"</td>" +
                        "<button class='btn btn-primary'>停用</button>" +
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

//    -------------------------------------以下为已经停用的汽车-------------------------------
//  点击查看所有的已经 停用的汽车 flag=1
    $('li.flag_1').click(function () {
        $('div.content.table-responsive.table-full-width').empty();

        $('li.flag_0').attr("class","flag_0");
        $('li.flag_1').attr("class","active flag_1");
        $('li.flag_2').attr("class","flag_2");


        var page=1;
        var body={startNum:page,num:10};
        $('input[type=hidden]').attr("value",page);

        $.ajax({
            type: 'POST',
            url: '/web/search/findNotUseBus',
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
                    var thead="<table class='table table-striped'>" +
                        "<thead>" +
                        "<th>车次</th>" +
                        "<th>车牌号</th>" +
                        "<th>车辆类型</th>" +
                        "<th>所有人</th>" +
                        "<th>使用性质</th>" +
                        "<th>品牌型号</th>" +
                        "<th>识别代号</th>" +
                        "<th>发动机号码</th>" +
                        "<th>核载人数</th>" +
                        "<th>操作</th>" +
                        "</thead>" +
                        "<tbody>" +
                        "</tbody>";
                    $('div.content.table-responsive.table-full-width').html(thead);
                    var tbody="";
                    $(res.data).each(function (index,item) {

                        tbody+="<tr>"+
                            "<td>"+item.busNum+"</td>" +
                            "<td>"+item.busId+"</td>" +
                            "<td>"+item.busType+"</td>" +
                            "<td>"+item.busOwner+"</td>" +
                            "<td>"+item.characters+"</td>" +
                            "<td>"+item.model+"</td>" +
                            "<td>"+item.vin+"</td>" +
                            "<td>"+item.engineNum+"</td>" +
                            "<td>"+item.seating+"</td>" +
                            "<button class='btn btn-primary'>启用</button>" +
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


    //可用车辆加载更多  flag=0
    $(document).on("click","div.panel.panel-success.more-flag1",function () {
        var page=$('input[type=hidden]').val();
        page=parseInt(page,10)+1;
        var body={startNum:page,num:10};
        $('input[type=hidden]').attr("value",page);
        $.ajax({
            type:'POST',
            url:'/web/search/waitcheckroute',
            contentType:"application/json;charset=utf-8",
            dataType:"json",
            data:JSON.stringify(body),
            error:function () {
                alert("加载失败，请刷新重试!");
            },
            success:function (res) {
                var tbody="";
                $(res.data).each(function (index,item) {
                    tbody+="<tr>"+
                        "<td>"+item.busNum+"</td>" +
                        "<td>"+item.busId+"</td>" +
                        "<td>"+item.busType+"</td>" +
                        "<td>"+item.busOwner+"</td>" +
                        "<td>"+item.characters+"</td>" +
                        "<td>"+item.model+"</td>" +
                        "<td>"+item.vin+"</td>" +
                        "<td>"+item.engineNum+"</td>" +
                        "<td>"+item.seating+"</td>" +
                        "<button class='btn btn-primary'>停用</button>" +
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

    //    -------------------------------------以下为已经停用的汽车-------------------------------
//  点击查看所有的已经 停用的汽车 flag=1
    $('li.flag_2').click(function () {
        $('div.content.table-responsive.table-full-width').empty();

        $('li.flag_0').attr("class","flag_0");
        $('li.flag_1').attr("class","flag_1");
        $('li.flag_2').attr("class","active flag_2");


        var page=1;
        var body={startNum:page,num:10};
        $('input[type=hidden]').attr("value",page);

        $.ajax({
            type: 'POST',
            url: '/web/search/findUsingBus',
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
                    var thead="<table class='table table-striped'>" +
                        "<thead>" +
                        "<th>车次</th>" +
                        "<th>车牌号</th>" +
                        "<th>车辆类型</th>" +
                        "<th>所有人</th>" +
                        "<th>使用性质</th>" +
                        "<th>品牌型号</th>" +
                        "<th>识别代号</th>" +
                        "<th>发动机号码</th>" +
                        "<th>核载人数</th>" +
                        "<th>操作</th>" +
                        "</thead>" +
                        "<tbody>" +
                        "</tbody>";
                    $('div.content.table-responsive.table-full-width').html(thead);
                    var tbody="";
                    $(res.data).each(function (index,item) {

                        tbody+="<tr>"+
                            "<td>"+item.busNum+"</td>" +
                            "<td>"+item.busId+"</td>" +
                            "<td>"+item.busType+"</td>" +
                            "<td>"+item.busOwner+"</td>" +
                            "<td>"+item.characters+"</td>" +
                            "<td>"+item.model+"</td>" +
                            "<td>"+item.vin+"</td>" +
                            "<td>"+item.engineNum+"</td>" +
                            "<td>"+item.seating+"</td>" +
                            "<button class='btn btn-primary'>启用</button>" +
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


    //可用车辆加载更多  flag=0
    $(document).on("click","div.panel.panel-success.more-flag2",function () {
        var page=$('input[type=hidden]').val();
        page=parseInt(page,10)+1;
        var body={startNum:page,num:10};
        $('input[type=hidden]').attr("value",page);
        $.ajax({
            type:'POST',
            url:'/web/search/findUsingBus',
            contentType:"application/json;charset=utf-8",
            dataType:"json",
            data:JSON.stringify(body),
            error:function () {
                alert("加载失败，请刷新重试!");
            },
            success:function (res) {
                var tbody="";
                $(res.data).each(function (index,item) {
                    tbody+="<tr>"+
                        "<td>"+item.busNum+"</td>" +
                        "<td>"+item.busId+"</td>" +
                        "<td>"+item.busType+"</td>" +
                        "<td>"+item.busOwner+"</td>" +
                        "<td>"+item.characters+"</td>" +
                        "<td>"+item.model+"</td>" +
                        "<td>"+item.vin+"</td>" +
                        "<td>"+item.engineNum+"</td>" +
                        "<td>"+item.seating+"</td>" +
                        "<button class='btn btn-primary'>停用</button>" +
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