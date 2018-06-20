package com.wxbus.wxController;

import com.wxbus.util.ResponseUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by g1154 on 2018/6/19.
 *
 * @page： com.wxbus.wxController
 * @Description:
 */
@RestController
@RequestMapping("/weixin/user")
public class WxUserController {

    @PostMapping("/changeUserInfo")
    public Object changInfo(@RequestBody String body){

        System.out.println(body);
        return ResponseUtil.ok();
    }
}
