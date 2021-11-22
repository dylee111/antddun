package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.DdunDTO;
import com.ds.antddun.entity.Ddun;
import com.ds.antddun.service.DdunService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class DdunController {

    private final DdunService ddunService;

    @ResponseBody
    @GetMapping("/mypage/wallet/save")
    public void walletSave(Long amount, DdunDTO ddunDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info("WALLET SAVE>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        log.info("CHARGE >>>" + amount);
        ddunDTO.setInputAmount(amount);
        ddunDTO.setMember(principalDetails.getMember().getMno());
        ddunDTO.setOutputAmount(0L);
        log.info("DTO >>>" + ddunDTO.getInputAmount());
        log.info("DTO >>>" + ddunDTO.getMember());
        log.info("DTO >>>" + ddunDTO.getDdunId());

        ddunService.saveDdun(ddunDTO);
    }
}