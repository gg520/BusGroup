package com.wxbus.webController;

import com.wxbus.daomain.Bus;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebSimpleJumpController {

    private final Log log= LogFactory.getLog(WebSimpleJumpController.class);

    @GetMapping("/index")
    public String toIndex()throws Exception{
        log.info("跳转到首页");
        return "index";
    }


    @GetMapping("/bus")
    public String toBusInformation()throws Exception{
        log.info("跳转到所有汽车");
        return "bus";
    }

    @GetMapping("/bus/add")
    public String addBus()throws Exception{
        log.info("跳转到添加汽车");
        return "addbus";
    }

    @GetMapping("/bus/allot")
    public String toAllotBus()throws Exception{
        log.info("跳转到分配汽车");
        return "allot";
    }
    @GetMapping("/station")
    public String toAllStation()throws Exception{
        log.info("跳转到所有站点信息页面");
        return "siteinfor";
    }
    @GetMapping("/station/add")
    public String toAddStation()throws Exception{
        log.info("跳转到添加站点");
        return "addsite";
    }
    @GetMapping("/line")
    public String toLine()throws Exception{
        log.info("跳转到线路信息");
        return "lineinfor";
    }
    @GetMapping("/line/add")
    public String toAddline()throws Exception{
        log.info("跳转到添加线路");
        return "addline";
    }
    @GetMapping("/line/exam")
    public String toExamLine()throws Exception{
        log.info("跳转到审核线路");
        return "examline";
    }
    @GetMapping("/driver")
    public String toDriver()throws Exception{
        log.info("跳转到司机信息");
        return "driverinfor";
    }
    @GetMapping("/staff")
    public String toStaff()throws Exception{
        log.info("跳转到职员信息");
        return "staffinfor";
    }
    @GetMapping("/staff/add")
    public String toAddStaff()throws Exception{
        log.info("跳转到添加管理员");
        return "addstaff";
    }
    @GetMapping("/driver/add")
    public String toAddDriver()throws Exception{
        log.info("跳转到添加司机");
        return "adddriver";
    }
    @GetMapping("/driver/exam")
    public String toExamDriver()throws Exception{
        log.info("跳转到审核司机");
        return "examdriver";
    }
    @GetMapping("/recruit")
    public String toRecruit()throws Exception{
        log.info("跳转到招募管理");
        return "recruit";
    }
    @GetMapping("/refund")
    public String toRefound()throws Exception{
        log.info("跳转到退款处理");
        return "refund";
    }
}
