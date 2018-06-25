$(document).ready(function(){

    var showTimes=0;
    $("input#submit").click(function () {


        var Station={};

        Station.stationName=$("input[name='stationName']").val();
        if(Station.stationName==""){
            alert("请输入站点名称");
            return false;
        }
        Station.stationCoord=$("input[name='stationCoord']").val();
        if(Station.stationCoord==""){
            alert("请选择站点坐标");
            return false;
        }

        $.ajax({
            type:'POST',
            url:'web/add/addstation',
            contentType:"application/json;charset=utf-8",
            DataType:"json",
            data:JSON.stringify(Station),
            error:function () {
                alert("加载失败，请刷新重试！");
            },
            success:function (res) {
                if(res.errno==0&&showTimes<=3){
                    showTimes+=1;
                    confirm("添加成功,是否继续");
                }else {
                    alert("添加失败,请刷新重试！");
                }

            }


        })
    })
})