package com.wxbus.service;

import com.wxbus.daomain.Message;

import java.util.List;

public interface MessageService {
    /**
     *@type interface
     *@parameter  [id, receivetype]
     *@back  java.util.List<com.wxbus.daomain.Message>
     *@author  如花
     *@creattime 2018/6/27
     *@describe 根据接收者id和接受者类型查找通知消息
     */
    List<Message> findMessageByIdReceivetype(Integer id, String receivetype);
}
