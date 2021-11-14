package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.PageResultDTO;
import com.ds.antddun.dto.QnaBoardDTO;
import com.ds.antddun.entity.JobList;
import com.ds.antddun.entity.QnaBoard;
import com.ds.antddun.repository.QnaBoardRepository;
import com.ds.antddun.service.JobListService;
import com.ds.antddun.service.QnaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@Log4j2
@SessionAttributes("member")
public class QnaBoardController {

    @Autowired
    private JobListService jobListService;

    @Autowired
    private QnaBoardRepository repository;

    @Autowired
    private QnaService qnaService;


    //게시물 작성
    @GetMapping("/member/qna/registerForm") //여기서는 principal로 한 번 더 확인
    public String register(QnaBoardDTO qnaBoardDTO, @AuthenticationPrincipal PrincipalDetails principal) {
        if (principal == null) {
            System.out.println("멤버권한이 없음");
            return "redirect:/login";
        } else {
            log.info("principal.getMember())" + principal.getMember());
        }
        return "/qna/registerForm";
    }

    //글 등록
    @PostMapping("/member/qna/register")
    public ModelAndView register(QnaBoardDTO qnaBoardDTO, Model model , @AuthenticationPrincipal PrincipalDetails principal) {
        qnaService.register(qnaBoardDTO, principal.getMember());
        ModelAndView mav = new ModelAndView("redirect:/qna/list");
        return mav;
    }

    //리스트로 값을 보냄(페이징 임시)
    @GetMapping("/qna/list")
    public String lists(Model model, PageRequestDTO pageRequestDTO,
                        RedirectAttributes redirect) {

        PageResultDTO<QnaBoardDTO, QnaBoard> result = qnaService.getBoardList(pageRequestDTO);
        List<JobList> list = jobListService.getList();
        model.addAttribute("jobList", list);
        model.addAttribute("result",result);
        log.info("controller result>>"+result);
        return "/qna/list";
    }



    //게시판 조회
    @GetMapping("/member/qna/read")
    public String read(){
        return "/member/qna/read";
    }


}