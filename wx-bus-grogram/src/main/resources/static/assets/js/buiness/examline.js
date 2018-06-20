$(document).ready(function () {
    //初次加载
    $('div.content.table-responsive.table-full-width').empty();
    var page=$('input[type=hidden]').val();
    var body={startNum:page*20+1,num:20};
    page=parseInt(page,10)+1;
    $('input[type=hidden]').attr("value",page);
    $.ajax({
        type:'POST',
        url:'/web/examline/toWaitCheck',
        contentType:"application/json;charset=utf-8",
        dataType:"json",
        data:JSON.stringify(body),
        error:function () {
            alert("加载失败，请刷新重试!");
        },
        success:function (res) {
            //如果没有返回数据
            if(res.length<0){

            }else{

                var html="<table class='table table-striped'>" +
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
                $('div.content.table-responsive.table-full-width').prepend(html);
                $(res).each(function (index,item) {

                    html="<tr>"+
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
                    $('tbody').append(html);
                })
            }
        }


    })


    //flag_1点击 点击已通过的线路
    $('li.flag_1').click(function () {
        $('input[type=hidden]').attr("value",0);
        $('div.content.table-responsive.table-full-width').empty();
        $('li.flag_1').attr("class","active flag_1");
        $('li.flag_0').attr("class","flag_0");
        $('li.flag_2').attr("class","flag_2");

        var page=$('input[type=hidden]').val();
        var body={startNum:page*20+1,num:20};
        page=parseInt(page,10)+1;
       // $('input[type=hidden]').attr("value",page);
        $.ajax({
            type:'POST',
            url:'/web/examline/toRunCheck',
            contentType:"application/json;charset=utf-8",
            dataType:"json",
            data:JSON.stringify(body),
            error:function () {
                alert("加载失败，请刷新重试!");
            },
            success:function (res) {
                //如果没有返回数据
                if(res.length<0){

                }else{

                    var html="<table class='table table-striped'>" +
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
                    $('div.content.table-responsive.table-full-width').prepend(html);
                    $(res).each(function (index,item) {
                        html="<tr>" +
                            "<td>"+item.routeId+"</td>" +
                            "<td>"+item.creatUser+"</td>" +
                            "<td>"+item.creatTime+"</td>" +
                            "<td>"+item.startSite+"</td>" +
                            "<td>"+item.endSite+"</td>" +
                            "<td>"+item.startTime+"</td>" +
                            "<td>"+item.endTime+"</td>" +
                            "<td>"+item.runTime+"</td>" +
                            "</tr>";
                        $('table.table.table-striped').append(html);
                    })
                }
            }


        })
    });

    //flag_2点击 点击未通过的线路
    $('li.flag_2').click(function () {
        $('input[type=hidden]').attr("value",0);
        $('div.content.table-responsive.table-full-width').empty();
        $('li.flag_2').attr("class","active flag_2");
        $('li.flag_0').attr("class","flag_0");
        $('li.flag_1').attr("class","flag_1");

        var page=$('input[type=hidden]').val();
        var body={startNum:page*20+1,num:20};
        page=parseInt(page,10)+1;
        // $('input[type=hidden]').attr("value",page);
        $.ajax({
            type:'POST',
            url:'/web/examline/toNotPass',
            contentType:"application/json;charset=utf-8",
            dataType:"json",
            data:JSON.stringify(body),
            error:function () {
                alert("加载失败，请刷新重试!");
            },
            success:function (res) {
                //如果没有返回数据
                if(res.length<0){

                }else{

                    var html="<table class='table table-striped'>" +
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
                    $('div.content.table-responsive.table-full-width').prepend(html);
                    $(res).each(function (index,item) {
                        html="<tr>" +
                            "<td>"+item.routeId+"</td>" +
                            "<td>"+item.creatUser+"</td>" +
                            "<td>"+item.creatTime+"</td>" +
                            "<td>"+item.startSite+"</td>" +
                            "<td>"+item.endSite+"</td>" +
                            "<td>"+item.startTime+"</td>" +
                            "<td>"+item.endTime+"</td>" +
                            "<td>"+item.runTime+"</td>" +
                            "</tr>";
                        $('table.table.table-striped').append(html);
                    })
                }
            }


        })
    });


    //flag_0点击 再次点击待审核的线路
    $('li.flag_0').click(function () {
        $('input[type=hidden]').attr("value",0);
        $('div.content.table-responsive.table-full-width').empty();
        $('li.flag_0').attr("class","active flag_0");
        $('li.flag_2').attr("class","flag_2");
        $('li.flag_1').attr("class","flag_1");

        var page=$('input[type=hidden]').val();
        var body={startNum:page*20+1,num:20};
        page=parseInt(page,10)+1;
        // $('input[type=hidden]').attr("value",page);
        $.ajax({
            type:'POST',
            url:'/web/examline/toWaitCheck',
            contentType:"application/json;charset=utf-8",
            dataType:"json",
            data:JSON.stringify(body),
            error:function () {
                alert("加载失败，请刷新重试!");
            },
            success:function (res) {
                //如果没有返回数据
                if(res.length<0){

                }else{

                    var html="<table class='table table-striped'>" +
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
                    $('div.content.table-responsive.table-full-width').prepend(html);
                    $(res).each(function (index,item) {
                        html="<tr>" +
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
                        $('table.table.table-striped').append(html);
                    })
                }
            }


        })
    });



});
