package com.wxbus.service.Impl;

import com.wxbus.dao.MessageMapper;
import com.wxbus.daomain.Message;
import com.wxbus.daomain.MessageExample;
import com.wxbus.service.MessageService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author: Demon
 * @date: 2018/6/27
 * @time: 16:02
 * Description:
 */
@Service
public class MessageServiceImpl implements MessageService{
    private final Log log= LogFactory.getLog(MessageServiceImpl.class.getName());
    @Resource
    private MessageMapper messageMapper;
    @Override
    /**
     *@type method
     *@parameter  [id, receivetype]
     *@back  java.util.List<com.wxbus.daomain.Message>
     *@author  如花
     *@creattime 2018/6/27
     *@describe 根据接收者id和接受者类型查找通知消息
     */
    public List<Message> findMessageByIdReceivetype(Integer id, String receivetype) {
        log.info("根据接收者id和接受者类型查找通知消息");
        MessageExample messageExample=new MessageExample();
        messageExample.or().andReceiverEqualTo(id).andReceivetypeEqualTo(receivetype);
        return messageMapper.selectByExampleWithBLOBs(messageExample);
    }

    @Override
    /**
     *@type method
     *@parameter  [message]
     *@back  void
     *@author  如花
     *@creattime 2018/6/28
     *@describe 更新消息
     */
    public void updateMessage(Message message) {
        log.info("更新消息");
        messageMapper.updateByPrimaryKeySelective(message);

    }

    @Override
    /**
     *@type method
     *@parameter  [id]
     *@back  com.wxbus.daomain.Message
     *@author  如花
     *@creattime 2018/6/28
     *@describe 根据主键查找消息
     */
    public Message findMessageById(Integer id) {
        log.info("根据主键查找消息");
        return messageMapper.selectByPrimaryKey(id);
    }
}