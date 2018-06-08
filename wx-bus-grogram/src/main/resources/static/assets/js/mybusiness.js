//审核线路
$(document).ready(function () {

    //任务审核
    $('.btn.exam-p').click(function () {

        //获取触发点击事件的组件的属性值，同时获取id值
        var id = $(this).attr('data-routeId');
        //节点遍历，前台修改
        $(this).parent("td").prev("td").text("通过");

    });


    $('.btn.exam-n').click(function () {


        //获取触发点击事件的组件的属性值，同时是id值
        var id = $(this).attr('data-routeId');
        //节点遍历，前台修改
        $(this).parent("td").prev("td").text("不通过");
    });
});

