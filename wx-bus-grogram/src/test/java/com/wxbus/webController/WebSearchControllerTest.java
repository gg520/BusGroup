package com.wxbus.webController;


import com.wxbus.util.JsonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
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
public class WebSearchControllerTest{
    @Autowired
    WebSearchController webSearchController;
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;
    @Before
    public  void setup(){
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }
    @Test
    public void waitCheckRoute() throws Exception{
        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("startNum",0);
        map.put("num",1);
        String body=JsonUtil.stringify(map);
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.post("/web/search/waitcheckroute").accept(MediaType.APPLICATION_JSON_UTF8).content(body))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getResponse().toString());
        /*Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("startNum",0);
        map.put("num",1);
        String body=JsonUtil.stringify(map);
        System.out.println("请求入参");
        RequestBuilder request = MockMvcRequestBuilders.post("uri").contentType(MediaType.APPLICATION_JSON_UTF8).
                header("SESSIONNO", "").content(body);*/
    }
    @Test
    public void runCheckRoute() throws Exception{
        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("startNum",0);
        map.put("num",1);
        String body=JsonUtil.stringify(map);
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.post("/web/search/runcheckroute").accept(MediaType.APPLICATION_JSON_UTF8).content(body))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getResponse().toString());
    }

    @Test
    public void notPass() throws Exception{
        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("startNum",0);
        map.put("num",1);
        String body=JsonUtil.stringify(map);
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.post("/web/examline/toNotPass").accept(MediaType.APPLICATION_JSON_UTF8).content(body))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getResponse().toString());
    }
    @Test
    public void findCanUseBus() throws Exception{
        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("startNum",0);
        map.put("num",20);
        String body=JsonUtil.stringify(map);
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.post("/web/examline/findCanUseBus").accept(MediaType.APPLICATION_JSON_UTF8).content(body))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getResponse().toString());
    }
    @Test
    public void findUsingStation() throws Exception{
        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("startNum",0);
        map.put("num",20);

        String body=JsonUtil.stringify(map);
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.post("/web/search/searchusingstation").accept(MediaType.APPLICATION_JSON_UTF8).content(body))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getResponse().toString());
    }
    @Test
    public void runingrouteTest() throws Exception{
        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("startNum",0);
        map.put("num",20);
        String body=JsonUtil.stringify(map);
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.post("/web/search/runingroute").accept(MediaType.APPLICATION_JSON_UTF8).content(body))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();

        System.out.println(result.getResponse().toString());
    }
    @Test
    public void findStopUseDriverTest() throws Exception{

        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("startNum",0);
        map.put("num",20);
        String body=JsonUtil.stringify(map);
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.post("/web/search/searchstopusedriver").accept(MediaType.APPLICATION_JSON_UTF8).content(body))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getResponse().toString());
    }
    @Test
    public void findAllAdminTest() throws Exception{
        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("startNum",0);
        map.put("num",20);
        String body=JsonUtil.stringify(map);
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.post("/web/search/findalladmin").accept(MediaType.APPLICATION_JSON_UTF8).content(body))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getResponse().toString());
    }
    @Test
    public void recruitingRoute() throws Exception{
        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("startNum",0);
        map.put("num",10);
        String body=JsonUtil.stringify(map);
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.post("/web/search/recruiting").accept(MediaType.APPLICATION_JSON_UTF8).content(body))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getResponse().toString());
    }
    @Test
    public void runtime() throws Exception{
        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("startNum",0);
        map.put("num",10);
        String body=JsonUtil.stringify(map);
        MvcResult result= mockMvc.perform(MockMvcRequestBuilders.post("/web/search/runtime").accept(MediaType.APPLICATION_JSON_UTF8).content(body))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print()).andReturn();
        System.out.println(result.getResponse().toString());
    }
}