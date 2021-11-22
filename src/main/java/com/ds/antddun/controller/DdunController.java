package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.DdunDTO;
import com.ds.antddun.entity.Ddun;
import com.ds.antddun.service.DdunService;
import com.ds.antddun.service.SosoJobService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class DdunController {

    private final DdunService ddunService;
    private final SosoJobService sosoJobService;

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

    @ResponseBody
    @GetMapping("/sosojob/buy/{sosoNo}")
    public void sosoBuy(@PathVariable("sosoNo") Long sosoNo,
                        @AuthenticationPrincipal PrincipalDetails principalDetails,
                        @RequestParam("amount") Long amount, DdunDTO ddunDTO) {

        log.info("SOSO BUY >>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        Long mno = sosoJobService.read(sosoNo).getMno();
        Long buyer = ddunService.totalAmountByMno(principalDetails.getMember().getMno());

        if (buyer >= amount) {
            ddunService.sosoBuy(principalDetails.getMember(), amount, ddunDTO);
            ddunService.sosoSell(mno, amount, ddunDTO);
        }
    }
}