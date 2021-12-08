package com.ds.antddun.service;

import com.ds.antddun.dto.MessageDTO;
import com.ds.antddun.entity.GroupBySender;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.Message;
import com.ds.antddun.entity.SosoJobBoard;

import java.util.List;

public interface MessageService {

    List<Message> getMessageListByMno(Long firstMember, Long secondMember);

    Long sendMsg(MessageDTO messageDTO, Long sosoNo, Member sender, Member receiver);

    List<Message> getMsgListByMno(Long mno);

    List<GroupBySender> distinctSender(Long sender);

    Long tradeCheck(int tradeCheck, MessageDTO messageDTO);

    int unreadMsg(Long loginMember);

    void readMsgChange(Long msgNo);

    void changeTradeState(boolean tradeState, Long sosoNo, Long sendMember);

    default Message dtoToEntity(MessageDTO messageDTO, Long sosoNo, Member sender, Member receiver) {

        Message message = Message.builder()
                .msgNo(messageDTO.getMsgNo())
                .msgTitle(messageDTO.getTitle())
                .msgContent(messageDTO.getContent())
                .sendMember(Member.builder().mno(sender.getMno()).build()) // 로그인 회원
                .receiveMember(Member.builder().mno(receiver.getMno()).build()) // sosojob 작성자
                .board(SosoJobBoard.builder().sosoNo(sosoNo).build()) // 게시글 번호
                .trade(messageDTO.isTrade())
                .sendTime(messageDTO.getCreateDate())
                .build();
        return message;
    }

}
