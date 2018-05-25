package com.wxbus.wxController;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by g1154 on 2018/5/22.
 */
@RestController
@RequestMapping("/weixin/search")
public class SearchController {

    @PostMapping("/site")
    public Object searchStation(@RequestBody String site){

        return null;
    }
}
