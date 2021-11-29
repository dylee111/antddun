package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.QnaReplyDTO;
import com.ds.antddun.service.QnaReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/member/")
@Log4j2
@RequiredArgsConstructor
public class QnaReplyController {
    private final QnaReplyService qnaReplyService;

    @PostMapping("/qna/list/replySave")
    public ResponseEntity<Long> replyRegister(@RequestBody QnaReplyDTO qnaReplyDTO, @AuthenticationPrincipal PrincipalDetails principal) {
        qnaReplyDTO.setMno(principal.getMember().getMno());
        Long qnaRno = qnaReplyService.register(qnaReplyDTO);

        return new ResponseEntity<>(qnaRno, HttpStatus.OK);
    }
/*
    @PostMapping("/sosojob/list/read/replyModify/{sosoReplyNo}")
    public ResponseEntity<String> replyModify(@PathVariable("sosoReplyNo") Long sosoReplyNo, @RequestParam("replyText")String replyText) {
        log.info("NO>>" + sosoReplyNo);
        log.info("TEXT>>" + replyText);
        sosoReplyService.replyModify(replyText, sosoReplyNo);
        return new ResponseEntity<>("replyModify", HttpStatus.OK);
    }*/
}
