$(document).ready(function () {
    var body={startNum:1,num:500};
    //查找所有已经有的站点
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
            var dropHeader="<div class='input-group'>" +
                "<input type='text'  name='characters' class='form-control border-input' required='required'>" +
                "<div class='input-group-btn'>" +
                "<button type='button' class='btn btn-default dropdown-toggle' data-toggle='dropdown' >" +
                "选择站点" +
                "<span class='caret'></span>" +
                "</button>" +
                "<ul class='dropdown-menu pull-right station'>" +
                "</ul>" +
                "</div>" +
                "</div>";
            $("div.form-group.station").append(dropHeader);
            var dropList="";
            $(res.data).each(function (index,item) {
                dropList+="<li class='selected' ><a href='#'>"+item.stationName+"</a></li>";
            })
            $("ul.dropdown-menu.pull-right.station").append(dropList);

        }
    })
    
    $(document).on("click","li.selected",function () {
        if($(this).parent("ul").parent("div").parent("div").prev("label").text()=="途径站"){
            if($(this).parent("ul").parent("div").prev("input").val()==""){
                $(this).parent("ul").parent("div").prev("input").val($(this).text());
                $(this).remove();
            }else{
                $(this).parent("ul").parent("div").prev("input").val($(this).parent("ul").parent("div").prev("input").val()+"、"+$(this).text());
                $(this).remove();
            }
        }else{
            $(this).parent("ul").parent("div").prev("input").val($(this).text());
        }

    })

})