$(document).ready(function () {

    var more="<div class='panel panel-success more'>"+
        "<div class='panel-heading'>" +
        "<center>" +
        "<h3 id='moreWait' class='panel-title'>点击加载更多数据</h3>" +
        "</center>" +
        "</div>" +
        "</div>";

    var noneleft="<div class='panel panel-success none'>" +
        "<div class='panel-heading'>" +
        "<center>" +
        "<h3 class='panel-title'>没有更多数据</h3>" +
        "</center>" +
        "</div>" +
        "</div>";
    //初次加载
    $('div.content.table-responsive.table-full-width').empty();
    var page=$('input[type=hidden]').val();
    var body={startNum:page*20+1,num:3};
    page=parseInt(page,10)+1;
    $('input[type=hidden]').attr("value",page);
    $.ajax({
        type:'POST',
        url:'/web/site/information',
        data:JSON.stringify(body),
        dataType:"json",
        contentType:"application/json;charset=utf-8",
        error:function () {
            alert("加载失败请刷新重试！");
        },
        success:function (res) {


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
            $('div.content.table-responsive.table-full-width').prepend(thead);
            var tbody="";
            $(res.data).each(function (index,item) {
                 tbody="<tr>" +
                    "<td>"+item.stationId+"</td>" +
                    "<td>"+item.stationName+"</td>\n" +
                    "<td>"+item.stationCoord+"</td>" +
                    "<td>"+item.stationStatus+"</td>" +
                    "<th><a class='btn '>停用</a><a class='btn '>启用</a></th>" +
                    "</tr>";
                $('tbody').append(tbody);
            })
            if(res.data.length<3){
                $('div.content.table-responsive.table-full-width').append(noneleft);
            }else {
                $('div.content.table-responsive.table-full-width').append(more);
            }

        }
    })

    $(document).on("click","div.panel.panel-success.more",function () {
        var page=$('input[type=hidden]').val();
        var body={startNum:page*20+1,num:5};
        page=parseInt(page,10)+1;
        $('input[type=hidden]').attr("value",page);
        $.ajax({
            type:'POST',
            url:'/web/site/information',
            data:JSON.stringify(body),
            dataType:"json",
            contentType:"application/json;charset=utf-8",
            error:function () {
                alert("加载失败请刷新重试！");
            },
            success:function (res) {
                
                var tbody="";
                $(res.data).each(function (index,item) {
                    tbody="<tr>" +
                        "<td>"+item.stationId+"</td>" +
                        "<td>"+item.stationName+"</td>\n" +
                        "<td>"+item.stationCoord+"</td>" +
                        "<td>"+item.stationStatus+"</td>" +
                        "<th><a class='btn '>停用</a><a class='btn '>启用</a></th>" +
                        "</tr>";
                    $('tbody').append(tbody);
                })
                if(eval(res.data.length)<eval(5)){
                    $("div.panel.panel-success.more").attr("class","panel panel-success none");
                    $("h3#moreWait").text("没有更多数据!");
                   // $('div.content.table-responsive.table-full-width').append(noneleft);
                }

            }
        })
    })


})