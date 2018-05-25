package com.wxbus.wxController;

import com.wxbus.daomain.Route;
import com.wxbus.service.RouteService;
import com.wxbus.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实现线路的controller
 * Created by g1154 on 2018/5/6.
 */
@RestController
@RequestMapping(value = "/weixin/route")
public class RouteController {
    @Autowired
    private RouteService routeService;

    private final Log logger= LogFactory.getLog(RouteController.class.getName());

    @RequestMapping(value = "/inquiryRoute",method = {RequestMethod.POST})
    public Object inquiryRoute(@RequestBody String body){

        System.out.println(body);
        logger.info("招募路线查询");

        return ResponseUtil.ok();
    }

    @RequestMapping(value="/routeInfo",method = {RequestMethod.POST})
    public Object routeInfo(@RequestBody  Integer routeId){
        logger.info("获取线路的详细信息:"+routeId);
        if(routeId==null){
            return ResponseUtil.fail();

        }
        else{
            Route route=routeService.findRouteById(routeId);

            return ResponseUtil.ok(route);


        }

    }

    /**
     * 获取正在招募的羡慕的信息，分页，每次获取10个
     * @param body
     *      body:
     *          pm:（0,1,2）不区分上午下午 0，上午 1，下午 2
     *          countRout： 当前以获取的数量
     *
     * @return
     */
    @RequestMapping(value = "/routePlant",method = {RequestMethod.POST})
    public Object routePlant(@RequestBody String body){

        logger.info("招募路线查询：" +body);
        body="{routes:[{ picUrl: '', startsite: '二七', endsite: '火车站', number: 10, nullnum: 15, isEditCart: false, busid: '', routeid: 1001, starttime:'07:30'}]}";
        return ResponseUtil.ok(body);
    }

    /**
     * 获取正在运行路线的信息
     * @param body
     *      body:
     *          pm:（0,1,2）不区分上午下午 0，上午 1，下午 2
     *          countRout： 当前已获取的数量
     * @return
     */
    @RequestMapping(value = "/routeRun",method = {RequestMethod.POST})
    public Object routeRun(@RequestBody String body){

        logger.info("招募路线查询：" +body);
        body="{routes:[{ picUrl: '', startsite: '二七', endsite: '火车站', number: 10, nullnum: 15, isEditCart: false, busid: '', routeid: 1001, starttime:'07:30'}]}";
        return ResponseUtil.ok(body);
    }
}
