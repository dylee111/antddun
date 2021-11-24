package com.ds.antddun.service;

import com.ds.antddun.dto.MessageDTO;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.Message;
import com.ds.antddun.entity.SosoJobBoard;

import java.util.List;

public interface MessageService {

    List<Message> getMessageListByMno(Long firstMember, Long secondMember);

    Long sendMsg(MessageDTO messageDTO, Member sender, Member receiver);

    List<Message> getMsgListByMno(Long mno);

    List<Member> distinctSender(Long sender);

    Long tradeCheck(Long msgNo);

    default Message dtoToEntity(MessageDTO messageDTO, Member sender, Member receiver) {
        Message message = Message.builder()
                .msgNo(messageDTO.getMsgNo())
                .msgTitle(messageDTO.getTitle())
                .msgContent(messageDTO.getContent())
                .sendMember(Member.builder().mno(sender.getMno()).build()) // 로그인 회원
                .receiveMember(Member.builder().mno(receiver.getMno()).build()) // sosojob 작성자
                .trade(messageDTO.isTrade())
                .sendTime(messageDTO.getCreateDate())
                .build();
        return message;
    }

}
