package com.ds.antddun.controller;


import com.ds.antddun.dto.JayuReplyDTO;
import com.ds.antddun.service.JayuReplyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Log4j2
@RequiredArgsConstructor
public class JayuReplyController {
    private final JayuReplyService jayuReplyService;

//    @GetMapping(value = "/member/jayu/read/jayuNo={jayuNo}", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<List<JayuReployDTO>> getListByJayuBoard(@PathVariable("jayuNo")Long jayuNo){
//        log.info("jayuNo: "+jayuNo);
//
//        return new ResponseEntity<>(jayuReplyService.getList(jayuNo), HttpStatus.OK);
//    }

    @RequestMapping("/reply/{jayuNo}")
    public ResponseEntity<Long> addReview(@RequestBody JayuReplyDTO jayuReplyDTO){
        log.info("--------------addReview1111---------------");
        log.info("reviewDTO1111: " + jayuReplyDTO );
        Long reviewNum = jayuReplyService.register(jayuReplyDTO);
        log.info("reviewDTO2222: " + jayuReplyDTO );
        log.info("--------------addReview222---------------"+reviewNum);
        return new ResponseEntity<>(reviewNum, HttpStatus.OK);
    }
}
