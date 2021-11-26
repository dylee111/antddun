package com.ds.antddun.controller;


import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.JayuReplyDTO;
import com.ds.antddun.service.JayuReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reply")
@Log4j2
@RequiredArgsConstructor
public class JayuReplyController {
    private final JayuReplyService jayuReplyService;

//    //댓글 목록
//    @GetMapping("/list")
//    public ResponseEntity<List<JayuReplyDTO>> getReplyByJayuNo(Long jayuNo){
//        List<JayuReplyDTO> jayuReplyDTOList = jayuReplyService.getReplyByJayuNo(jayuNo);
//
//        return new ResponseEntity<>(jayuReplyDTOList, HttpStatus.OK);
//    }

    //댓글 작성
    @PostMapping("/register")
    //Json(application/json)형태의 HTTP Body 내용을 Java Object로 변환시켜주는 역할
    public ResponseEntity<Long> replyRegister(@RequestBody JayuReplyDTO jayuReplyDTO,
                                         @AuthenticationPrincipal PrincipalDetails principal ){
        log.info("replyDTO: " + jayuReplyDTO);
        jayuReplyDTO.setMno(principal.getMember().getMno());
        log.info("jayuBoardDTO>>>>>>"+jayuReplyDTO);
        Long jayuRno = jayuReplyService.register(jayuReplyDTO);

        return new ResponseEntity<>(jayuRno, HttpStatus.OK);
    }

}
