package com.wxbus.wxController;

import com.wxbus.util.ResponseUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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

    private final Log logger= LogFactory.getLog(RouteController.class.getName());

    @RequestMapping(value = "/inquiryRoute",method = {RequestMethod.POST})
    public Object inquiryRoute(@RequestBody String body){

        System.out.println(body);
        logger.info("招募路线查询");

        return ResponseUtil.ok();
    }

    @RequestMapping(value="/routeInfo",method = {RequestMethod.POST})
    public Object routeInfo( @RequestBody String routeid){
        logger.info("获取线路的详细信息:"+routeid);

        String body="{" +
                " starttime:'07:50'," +//出发时间
                " arrivaltime:'08:10'," +//预计到达时间
                " distance:6.6," +//距离
                " price:3.00," +//单价
                " monthNum:20," +//每月发车次数
                " driver:'待定'," +//司机
                " phone:'待定'," +//电话
                " busNum:'待定'," +//车牌
                " stations:[ '二七','龙湖','火车站']," +//车站的站点，有顺序
                "}";

        return ResponseUtil.ok(body);
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
