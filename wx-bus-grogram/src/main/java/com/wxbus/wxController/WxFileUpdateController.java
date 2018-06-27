package com.wxbus.wxController;

import com.sun.imageio.plugins.common.ImageUtil;
import com.wxbus.daomain.Passenger;
import com.wxbus.service.HeadersName;
import com.wxbus.service.UserService;
import com.wxbus.service.UserTokenManager;
import com.wxbus.util.JacksonUtil;
import com.wxbus.util.ResponseUtil;
import com.wxbus.util.UUIDUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * @author: Demon
 * @date: 2018/6/24
 * @time: 10:03
 * Description:
 */
@RestController
@RequestMapping(value = "/weixin/file")
public class WxFileUpdateController {
    private final Log logger= LogFactory.getLog(WxFileUpdateController.class.getName());
    @Autowired
    private UserService userService;
    @Autowired
    private ResourceLoader resourceLoader;
    @Autowired
    public WxFileUpdateController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    @RequestMapping(value = "submitPicture",method ={RequestMethod.GET,RequestMethod.POST})
    /**
     *@type method
     *@parameter  [body, request, model, passengeravater]
     *@back  java.lang.Object
     *@author  如花
     *@creattime 2018/6/24
     *@describe 上传图片
     */
    public Object submitPicture( HttpServletRequest request,  MultipartFile passengeravater) {
        logger.info("上传图片");
        String token=request.getHeader(HeadersName.TOKEN);
        String json= UserTokenManager.getUserId(token);
        Integer userId=Integer.valueOf(JacksonUtil.parseString(json,"userId"));
        Passenger passenger=userService.findById(userId);
        if(passenger==null){
            return ResponseUtil.fail(500,"不存在用户");
        }
        //原始名称
        String originalFilename=passengeravater.getOriginalFilename();
        if(passengeravater!=null && originalFilename!=null && originalFilename.length()>0){
            String pic_path=this.getClass().getClassLoader().getResource("").getPath()+"/image/";
            //新图片名称
            String newFileName= UUIDUtil.getUUID()+originalFilename.substring(originalFilename.lastIndexOf("."));
            try {
                File newFile=new File(pic_path+newFileName);
                passengeravater.transferTo(newFile);
            }catch (IOException e){
                e.printStackTrace();
            }
            passenger.setPassengerAvater(pic_path+newFileName);
            userService.updatePassenger(passenger);
            return ResponseUtil.ok("上传成功");
        }
        return ResponseUtil.fail(500,"上传失败");
    }
}