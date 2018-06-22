$(document).ready(function () {

   $("li.si").click(function () {
       var val1=$(this).text();
       $(this).parent("ul").parent("div").prev("input").val(val1);
   })


    $("li.go").click(function () {
        var val1=$(this).text();
        $(this).parent("ul").parent("div").prev("input").val(val1);
    })

    $(document).on("click","input#submit",function () {
        var Bus={}
        Bus.busId=$("input[name='busId']").val();
        if(Bus.busId==""){
            alert("请输入车牌号");
            return false;
        }

        Bus.busType=$("input[name='busType']").val();
        if(Bus.busType=="") {
            alert("请输入车辆类型");
            return false;
        }

        Bus.busOwner=$("input[name='busOwner']").val();
        if(Bus.busOwner==""){
            alert("请输入车辆拥有者");
            return false;
        }

        Bus.characters=$("input[name='characters']").val();
        if(Bus.characters==""){
            alert("请选择使用性质");
            return false;
        }

        Bus.model=$("input[name='model']").val();
        if(Bus.model==""){
            alert("请输入品牌型号");
            return false;
        }

        Bus.vin=$("input[name='vin']").val();
        if(Bus.vin==""){
            alert("请输入车辆识别号");
            return false;
        }

        Bus.engineNum=$("input[name='engineNum']").val();
        if(Bus.engineNum==""){
            alert("请输入发动机号码");
            return false;
        }

        Bus.registrationDate=$("input[name='registrationDate']").val();
        if(Bus.registrationDate==""){
            alert("请输入注册日期");
            return false;

        }

        Bus.oppenDate=$("input[name='oppenDate']").val();
        if(Bus.oppenDate==""){
            alert("请输入发证日期");
            return false;

        }

        Bus.seating=$("input[name='seating']").val();
        if(Bus.seating==""){
            alert("请输入座位数");
            return false;
        }

        $.ajax({
            type:'POST',
            url:'web/add/addbus',
            contentType:"application/json;charset=utf-8",
            DataType:"json",
            data:JSON.stringify(Bus),
            error:function () {
                alert("加载失败，请刷新重试！");
            },
            success:function (res) {
                if(res.errno==0){
                    confirm("添加成功");
                }else {
                    alert("添加失败,请刷新重试！");
                }

            }


        })


        
    })


})