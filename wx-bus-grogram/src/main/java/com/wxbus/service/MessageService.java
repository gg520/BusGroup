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

    /**
     *@type interface
     *@parameter  [message]
     *@back  void
     *@author  如花
     *@creattime 2018/6/28
     *@describe 更新消息表
     */
    void updateMessage(Message message);
    /**
     *@type interface
     *@parameter  [id]
     *@back  com.wxbus.daomain.Message
     *@author  如花
     *@creattime 2018/6/28
     *@describe 根据主键查找通知消息
     */
    Message findMessageById(Integer id);
}
