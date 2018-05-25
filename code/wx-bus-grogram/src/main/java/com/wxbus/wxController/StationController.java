package com.wxbus.wxController;

import com.wxbus.daomain.Station;
import com.wxbus.service.StationService;
import com.wxbus.util.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Demon
 * @date: 2018/5/17
 * @time: 20:08
 * Description:
 */
@RestController
@RequestMapping(value="/weixin/station")
public class StationController {

    @Autowired
    private StationService stationService;

    @RequestMapping(value="/queryStation",method={RequestMethod.GET})
    public  Object queryStation(@RequestBody String endSiteName) throws  Exception{
        if (endSiteName!=null||!"".equals(endSiteName)){
            List<Station> stationList=new ArrayList<Station>();
            stationList=stationService.findStationByName(endSiteName);
            return ResponseUtil.ok(stationList);

        }
        else{
            return ResponseUtil.fail();
        }

    }
}