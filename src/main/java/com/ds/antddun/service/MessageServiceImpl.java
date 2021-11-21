package com.ds.antddun.service;

import com.ds.antddun.dto.MessageDTO;
import com.ds.antddun.entity.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{
    @Override
    public List<MessageDTO> getMessageList(Message message) {
        return null;
    }

    @Override
    public List<Message> sendMsg() {
        return null;
    }
}
