package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.QnaReplyDTO;
import com.ds.antddun.service.QnaReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/member/")
@Log4j2
@RequiredArgsConstructor
public class QnaReplyController {

    private final QnaReplyService qnaReplyService;

    @PostMapping("/qna/list/replySave")
    public ResponseEntity<Long> replyRegister(@RequestBody QnaReplyDTO qnaReplyDTO, @AuthenticationPrincipal PrincipalDetails principal) {

        qnaReplyDTO.setMno(principal.getMember().getMno());
        log.info("whyyyyy"+ qnaReplyDTO);

        Long qnaRno = qnaReplyService.register(qnaReplyDTO);
        return new ResponseEntity<>(qnaRno, HttpStatus.OK);
    }

    @PostMapping("/qna/list/read/replyModify/{qnaRno}")
    public ResponseEntity<String> replyModify(@PathVariable("qnaRno") Long qnaRno,
                                              @RequestBody QnaReplyDTO qnaReplyDTO) {
        log.info("NO>>" + qnaRno);
        qnaReplyService.replyModify(qnaReplyDTO);
        return new ResponseEntity<>("replyModify", HttpStatus.OK);
    }

    @PostMapping("/qna/list/replyDelete/{qnaRno}")
    public ResponseEntity<String> replyDelete(@PathVariable("qnaRno") Long qnaRno) {
        log.info("REPLYNO>>> "+ qnaRno);
        qnaReplyService.deleteByQnaRno(qnaRno);
        return new ResponseEntity<>("delete", HttpStatus.OK);
    }

/*    //뚠 채택
    @PostMapping("/qna/selected/{replyNo}")
    public ResponseEntity<String> selectAnswer(@PathVariable("replyNo") Long replyNo,
                                               @PathVariable("replier") Long replier) {
        log.info("replyNo>>" + replyNo);
        log.info("replier>>" + replier);
        return new ResponseEntity<>("selected", HttpStatus.OK);
    }*/



}
