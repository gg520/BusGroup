$(document).ready(function () {

   $("li.si").click(function () {
       var val1=$(this).text();
       $(this).parent("ul").parent("div").prev("input").val(val1);
   })


    $("li.go").click(function () {
        var val1=$(this).text();
        $(this).parent("ul").parent("div").prev("input").val(val1);
    })

    $("botton.btn.btn-info.btn-fill.btn-wd.submit").click(function () {

        
    })


})