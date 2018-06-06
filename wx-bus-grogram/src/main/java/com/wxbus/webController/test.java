package com.wxbus.webController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by g1154 on 2018/5/12.
 */

@Controller
@RequestMapping("/web")
public class test {

    @GetMapping(value = "/hello")
    public String test(){

        System.out.println("hello");
        return "hello";
    }
}
