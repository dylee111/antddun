package com.ds.antddun.service;

import com.ds.antddun.dto.MessageDTO;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.Message;
import com.ds.antddun.entity.SosoJobBoard;
import com.ds.antddun.repository.DdunRepository;
import com.ds.antddun.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

    private final MessageRepository messageRepository;

    @Override
    public List<MessageDTO> getMessageList(Message message) {
        return null;
    }

    @Override
    public Long sendMsg(MessageDTO messageDTO, Member sender, Member receiver) {
        messageDTO.setSendMno(sender.getMno());
        messageDTO.setReceiveMno(receiver.getMno());
        log.info("S.CONTENT" + messageDTO.getContent());
        log.info("S.TITLE" + messageDTO.getTitle());
        Message message = dtoToEntity(messageDTO, sender, receiver);

        return messageRepository.save(message).getMsgNo();
    }

    @Override
    public List<Message> getMsgListByMno(Long mno) {

        List<Message> result = messageRepository.getMsgListByMno(mno);

        return result;
    }
}
