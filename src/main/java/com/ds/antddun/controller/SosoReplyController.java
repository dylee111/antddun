package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.SosoReplyDTO;
import com.ds.antddun.service.SosoReplyService;
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
@RequestMapping("/member")
@Log4j2
@RequiredArgsConstructor
public class SosoReplyController {
    private final SosoReplyService sosoReplyService;

    @PostMapping("/sosojob/list/replySave")
    public ResponseEntity<Long> replyRegister(@RequestBody SosoReplyDTO sosoReplyDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        sosoReplyDTO.setMno(principalDetails.getMember().getMno());
        Long sosoReplyNo = sosoReplyService.register(sosoReplyDTO);

        return new ResponseEntity<>(sosoReplyNo, HttpStatus.OK);
    }

}
