package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.MessageDTO;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.Message;
import com.ds.antddun.service.MessageService;
import com.ds.antddun.service.SosoJobService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/member")
public class MessageController {

    @Autowired
    private MessageService messageService;
    @Autowired
    private SosoJobService sosoJobService;

    @GetMapping("/messenger")
    public String messenger(Model model, @AuthenticationPrincipal PrincipalDetails principal) {
        List<Message> msgList = messageService.getMsgListByMno(principal.getMember().getMno());
        List<Member> memberList = new ArrayList<>();

        for (int i = 0; i < msgList.size(); i++) {
            memberList.add(memberList.get(i));
        }
        log.info("MEMBERLIST >>> " + memberList);
        if (principal != null) {
            model.addAttribute("member", principal.getMember());
            model.addAttribute("msgList", messageService.getMsgListByMno(principal.getMember().getMno()));
        }
        return "member/messenger";
    }

    @ResponseBody
    @PostMapping("/messenger/sendMessage/{mno}")
    public void sendMsg(@PathVariable("mno") Long mno,
                        MessageDTO messageDTO,
                        @AuthenticationPrincipal PrincipalDetails principalDetails, HttpServletRequest http) {
        log.info("SEND MESSAGE>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("TITLE>>> " + http.getParameter("msgTitle"));
        log.info("Content>>> " + http.getParameter("msgContent"));
        log.info("MNO" + mno);
        messageDTO.setTitle(http.getParameter("msgTitle"));
        messageDTO.setContent(http.getParameter("msgContent"));
        log.info("C.CONTENT" + messageDTO.getContent());
        log.info("C.TITLE" + messageDTO.getTitle());

        Member receiver = Member.builder().mno(mno).build();

        messageService.sendMsg(messageDTO, principalDetails.getMember(), receiver);
    }
}
