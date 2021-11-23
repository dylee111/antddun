package com.ds.antddun.service;

import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.Message;
import com.ds.antddun.repository.MessageRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class MessageServiceImplTest {

    @Autowired
    private MessageRepository messageRepository;

    @Test
    void getMsgListByMno() {
        List<Message> result = messageRepository.getMsgListByMno(35L);
        List<Member> result2 = new ArrayList<>();
        System.out.println(result);
        System.out.println(result.size());
        for (int i = 0; i < result.size(); i++) {
            result2.add(result.get(i).getSendMember());
            result2.add(result.get(i).getReceiveMember());
        }
        System.out.println(result2);
    }
}