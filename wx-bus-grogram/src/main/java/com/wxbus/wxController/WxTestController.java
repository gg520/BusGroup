package com.wxbus.wxController;

import com.wxbus.util.ResponseUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by g1154 on 2018/6/19.
 *
 * @page： com.wxbus.wxController
 * @Description:
 */
@RestController
@RequestMapping("/weixin/test")
public class WxTestController {

    @RequestMapping(value = "/test2",method = {RequestMethod.POST,RequestMethod.GET})
    public Object test(@RequestBody String body){

        System.out.println(body);

        return ResponseUtil.ok();
    }
    @RequestMapping(value = "/test1",method = {RequestMethod.POST,RequestMethod.GET})
    public Object test(){

        System.out.println("无参");

        return ResponseUtil.ok();
    }
}
