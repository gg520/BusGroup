package com.wxbus.webController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Demon
 * @date: 2018/5/31
 * @time: 20:29
 * Description:
 */
@RestController
@RequestMapping(value = "/web/add")
public class WebAddController {
    @RequestMapping(value = "/toaddbus")
    public String addBus() {
        return "addbus";
    }

    @RequestMapping(value = "/toadddriver")
    public String addDriver() {
        return "adddriver";
    }

    @RequestMapping(value = "/toaddline")
    public String addLine() {
        return "addline";
    }

    @RequestMapping(value = "/toaddsite")
    public String addSite() {
        return "addsite";
    }

    @RequestMapping("/toaddstaff")
    public String addStaff() {
        return "addstaff";
    }

}