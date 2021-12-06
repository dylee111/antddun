package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.MessageDTO;
import com.ds.antddun.entity.GroupBySender;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.Message;
import com.ds.antddun.service.MessageService;
import com.ds.antddun.service.SosoJobService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

//        List<Member> senderList = messageService.distinctSender(principal.getMember().getMno());
        List<GroupBySender> senderList = messageService.groupBySendMember(principal.getMember().getMno());
        if (principal != null) {
            model.addAttribute("member", principal.getMember());
            model.addAttribute("msgList", messageService.getMsgListByMno(principal.getMember().getMno()));
            model.addAttribute("senderList", senderList);
            log.info("SENDER >>> " + senderList.get(0).getTrade());
            log.info("SENDER >>> " + senderList.get(0).getSenderMno());
            log.info("SENDER >>> " + senderList.get(0).getBoard());
        }
        return "member/messenger";
    }

    @ResponseBody
    @PostMapping("/messenger/sendMessage/{mno}")
    public void sendMsg(@PathVariable("mno") Long mno,
                        MessageDTO messageDTO,
                        @AuthenticationPrincipal PrincipalDetails principalDetails, HttpServletRequest http) {

        Long sosoNo = Long.valueOf(http.getParameter("board"));
        messageDTO.setTitle(http.getParameter("msgTitle"));
        messageDTO.setContent(http.getParameter("msgContent"));
        messageDTO.setBoard(sosoNo);
        log.info("BOARDNO1>>>> " + Long.valueOf(http.getParameter("board")));
        log.info("BOARDNO2>>>> " + messageDTO.getBoard());

        Member receiver = Member.builder().mno(mno).build();

        messageService.sendMsg(messageDTO, sosoNo, principalDetails.getMember(), receiver);
    }

    @ResponseBody
    @GetMapping(value = "/messenger/{mno}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Message>> getListByMno(@PathVariable("mno") Long mno,
                                                      @AuthenticationPrincipal PrincipalDetails principalDetails) {

        List<Message> result = messageService.getMessageListByMno(principalDetails.getMember().getMno(), mno);
        log.info("MSGLIST>>>" + result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @PostMapping("/messenger/tradeCheck")
    public ResponseEntity<String> tradeCheck(@RequestParam("trade") int tradeCheck, MessageDTO messageDTO) {
        messageService.tradeCheck(tradeCheck, messageDTO);

        return new ResponseEntity<>("tradeChange", HttpStatus.OK);
    }

    // 읽은 메시지 확인
    @ResponseBody
    @PostMapping("/messenger/readCheck/{msgNo}")
    public void readCheck(@PathVariable("msgNo") Long msgNo) {
        messageService.readMsgChange(msgNo);
    }
}
