package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.JayuBoardDTO;
import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.PageResultDTO;
import com.ds.antddun.entity.JayuBoard;
import com.ds.antddun.service.JayuBoardService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
public class JayuBoardController {

    @Autowired
    private JayuBoardService jayuBoardService;

    //게시글 작성
    @GetMapping("/member/jayu/register")
    public String register(@AuthenticationPrincipal PrincipalDetails principal){

        if (principal == null) {
            return "redirect:/login";
        } else {
            log.info("principal.getMember())" + principal.getMember());
        }

        return "jayu/register";
    }

    //게시글 등록
    @PostMapping("/member/jayu/register")
    public String register(JayuBoardDTO jayuBoardDTO, RedirectAttributes redirectAttributes, @AuthenticationPrincipal PrincipalDetails principal){
        log.info("register.....");
        Long jayuNo = jayuBoardService.register(jayuBoardDTO, principal.getMember());
        redirectAttributes.addFlashAttribute("jayuNo", jayuNo);
        log.info(">>>>>"+jayuBoardDTO);
        return "redirect:/member/jayu/read/jayuNo=" + jayuNo+ "";
    }

    //게시글 조회
    @GetMapping("/member/jayu/read/jayuNo={jayuNo}")
    public String read(Model model, @PathVariable Long jayuNo, @AuthenticationPrincipal PrincipalDetails principal,
                       @ModelAttribute("pageRequestDTO") PageRequestDTO pageRequestDTO) {

        if (principal == null) {
            return "redirect:/login";
        } else {
            log.info("principal.getMember())" + principal.getMember());
        }

        JayuBoardDTO jayuBoardDTO = jayuBoardService.read(jayuNo);
        model.addAttribute("jayuBoardDTO", jayuBoardDTO);
        log.info("read......");
        return "/jayu/read";
    }

    //게시글 목록
    @GetMapping("/jayu/list")
    public String list(Model model, PageRequestDTO pageRequestDTO) {
        PageResultDTO<JayuBoardDTO, JayuBoard> jayuList = jayuBoardService.getList(pageRequestDTO);
        List<JayuBoardDTO> list = jayuList.getDtoList();
        for (int i = 0; i < list.size(); i++) {
            JayuBoardDTO tmp = (JayuBoardDTO) list.get(i);
            tmp.setContent(tmp.getContent().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
            list.set(i, tmp);
        }
        jayuList.setDtoList((List<JayuBoardDTO>) list);
        model.addAttribute("jayuList",jayuList);
        return "/jayu/list";
    }
}