package com.wxbus.webController;

import com.wxbus.daomain.Station;
import com.wxbus.util.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockSessionCookieConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@AutoConfigureMockMvc
public class WebAddControllerTest {
    @Autowired
    WebAddController webAddController;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public  void setup() throws Exception{
        //MockMvcBuilders使用构建MockMvc对象   （项目拦截器有效,从上下文和获取）
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

//单个类  拦截器无效

//         mockMvc = MockMvcBuilders.standaloneSetup(webAddController).build();

    }

    @Test
    public void toAddBus() {
    }

    @Test
    public void toAddDriver() {
    }

    @Test
    public void toAddLine() {
    }

    @Test
    public void toAddSite() {
    }

    @Test
    public void toAddStaff() {
    }

    @Test
    public void addLine() throws Exception{
        Map<Object,Object> map=new HashMap<Object, Object>();
        map.put("startSite","南天门");
        map.put("endSite","瑶池");
        map.put("stationId","1,5,3,4");
        map.put("startTime","06:12");
        map.put("endTime","15:33");
        map.put("runTime","54");
        String body=JsonUtil.stringify(map);
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.post("/web/add/addline").accept(MediaType.APPLICATION_JSON_UTF8).contentType(MediaType.APPLICATION_JSON_UTF8).content(body))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getResponse().toString());
    }

}