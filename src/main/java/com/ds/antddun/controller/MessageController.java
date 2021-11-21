package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.MemberDTO;
import com.ds.antddun.service.MessageService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/member")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/messenger")
    public String messenger (Model model, @AuthenticationPrincipal PrincipalDetails principal) {
        if(principal != null) {
            model.addAttribute("member", principal.getMember());
        }
        return "member/messenger";
    }
}
