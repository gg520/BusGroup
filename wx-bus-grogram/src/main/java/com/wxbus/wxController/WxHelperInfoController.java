package com.wxbus.wxController;

import com.wxbus.daomain.Helper;
import com.wxbus.service.HelperService;
import com.wxbus.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Demon
 * @date: 2018/6/22
 * @time: 10:55
 * Description:
 */
@RestController
@RequestMapping(value = "/weixin/help")
public class WxHelperInfoController {
    @Autowired
    HelperService helperService;
    private final Log logger= LogFactory.getLog(WxHelperInfoController.class.getName());
    @RequestMapping(value = "helpList",method = RequestMethod.POST)
    public Object getRandomHelper(){
        List<Helper> helperList= helperService.randomSearchHelper();
        return ResponseUtil.ok(helperList);

    }


}