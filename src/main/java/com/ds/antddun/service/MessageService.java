package com.ds.antddun.service;

import com.ds.antddun.dto.MessageDTO;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.Message;
import com.ds.antddun.entity.SosoJobBoard;

import java.util.List;

public interface MessageService {

    List<MessageDTO> getMessageList(Message message);

    List<Message> sendMsg();


    default Message dtoToEntity(MessageDTO messageDTO, Member member, SosoJobBoard sosoJobBoard) {
        Message message = Message.builder()
                .msgNo(messageDTO.getMsgNo())
                .title(messageDTO.getTitle())
                .content(messageDTO.getContent())
                .sendMember(member) // 로그인 회원
                .receiveMember(sosoJobBoard.getMember()) // sosojob 작성자. 이게 맞나..........
                .trade(messageDTO.isTrade())
                .sendTime(messageDTO.getCreateDate())
                .build();
        return message;
    }

}
