package com.ds.antddun.service;

import com.ds.antddun.dto.MessageDTO;
import com.ds.antddun.entity.GroupBySender;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.Message;
import com.ds.antddun.entity.QMessage;
import com.ds.antddun.repository.MessageRepository;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService{

    @PersistenceContext
    private EntityManager entityManager;

    private final MessageRepository messageRepository;
    /* 회원 간 주고 받은 메세지 목록 */
    @Override
    public List<Message> getMessageListByMno(Long firstMember, Long secondMember) {

        List<Message> result = messageRepository.getList(firstMember, secondMember);

        return result;
    }

    /* 메세지 전송 */
    @Override
    public Long sendMsg(MessageDTO messageDTO, Long sosoNo, Member sender, Member receiver) {

        messageDTO.setSendMno(sender.getMno());
        messageDTO.setReceiveMno(receiver.getMno());
        log.info("S.CONTENT" + messageDTO.getContent());
        log.info("S.TITLE" + messageDTO.getTitle());
        Message message = dtoToEntity(messageDTO, sosoNo, sender, receiver);

        return messageRepository.save(message).getMsgNo();
    }

    /* 회원 전체 메세지 목록 */
    @Override
    public List<Message> getMsgListByMno(Long mno) {

        List<Message> result = messageRepository.getMsgListByMno(mno);

        return result;
    }

    /* 메시지 중복 제외 */
    @Override
    public List<GroupBySender> distinctSender(Long sender) {
        List<GroupBySender> result = messageRepository.distinctSender(sender);

        return result;
    }

    @Override
    public Long tradeCheck(int tradeCheck, MessageDTO messageDTO) {
        Message message;

        if (tradeCheck == 0) {
            messageDTO.setTrade(false);
            message = Message.builder().trade(messageDTO.isTrade()).build();
            return message.getMsgNo();
        } else if(tradeCheck == 1){
            messageDTO.setTrade(true);
            message = Message.builder().trade(messageDTO.isTrade()).build();
            return message.getMsgNo();
        }
            return null;
    }

    /* 안읽은 메시지 */
    @Override
    public int unreadMsg() {
        return messageRepository.unreadMsg();
    }

    @Transactional
    @Override
    public void readMsgChange(Long msgNo) {
        messageRepository.readMsgChange(msgNo);
    }

    @Transactional
    @Override
    public void changeTradeState(boolean tradeState, Long sosoNo, Long sendMember) {
        messageRepository.changeTradeState(tradeState, sosoNo, sendMember);
    }

    @Override
    public List<GroupBySender> groupBySendMember(Long receiveMember) {

        QMessage qMessage = QMessage.message;

        JPAQueryFactory qf = new JPAQueryFactory(entityManager);

        JPAQuery<GroupBySender> query = qf.from(qMessage)
                .groupBy(qMessage.sendMember).groupBy(qMessage.board)
                .select(
                        Projections.bean(
                                GroupBySender.class,
                                qMessage.sendMember.as("sendMno"),
                                qMessage.board.as("board"),
                                qMessage.trade.as("trade")
                        )

                )
                .where(qMessage.sendMember.mno.eq(receiveMember)
                        .and(qMessage.sendMember.mno.ne(qMessage.receiveMember.mno))
                );

        return query.fetch();
    }
}
