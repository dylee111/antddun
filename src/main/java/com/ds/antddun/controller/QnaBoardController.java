package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.PageRequestDTO;
import com.ds.antddun.dto.PageResultDTO;
import com.ds.antddun.dto.QnaBoardDTO;
import com.ds.antddun.entity.JobList;
import com.ds.antddun.entity.QnaBoard;
import com.ds.antddun.repository.QnaBoardRepository;
import com.ds.antddun.repository.QnaLikesRepository;
import com.ds.antddun.service.JobListService;
import com.ds.antddun.service.QnaService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private QnaBoardRepository qnaBoardRepository;

    @Autowired
    private QnaService qnaService;

    @Autowired
    private QnaLikesRepository qnaLikesRepository;


    //리스트 출력
    @GetMapping("/qna/list")
    public String list(Model model, PageRequestDTO pageRequestDTO, @AuthenticationPrincipal PrincipalDetails principal) {

        List<JobList> list = jobListService.getList();
        model.addAttribute("jobList", list);
        model.addAttribute("boardList", qnaService.getList(pageRequestDTO));

        return "/qna/list";
    }


    //게시물 작성 양식
    @GetMapping("/member/qna/registerForm")
    public String register(QnaBoardDTO qnaBoardDTO,@AuthenticationPrincipal PrincipalDetails principal,Model model) {
        if (principal == null) {
            System.out.println("멤버권한이 없음");
            return "redirect:/login";
        } else {
            log.info("principal.getMember())" + principal.getMember());
        }
        model.addAttribute("jobList", jobListService.getList());
        return "/qna/registerForm";
    }

    //글 등록
    @PostMapping("/member/qna/register")
    public ModelAndView register(QnaBoardDTO qnaBoardDTO, Model model, @PathVariable Long qnaNo,
                                 @AuthenticationPrincipal PrincipalDetails principal) {
        qnaService.register(qnaBoardDTO, principal.getMember());
        ModelAndView mav = new ModelAndView("redirect:/member/qna/read/{qnaNo}");
        return mav;
    }


    //게시판 조회
    @GetMapping("/member/qna/read/{qnaNo}")
    public String read(QnaBoardDTO qnaBoardDTO, Model model, @PathVariable Long qnaNo){
        QnaBoard qnaBoard = qnaBoardRepository.getById(qnaNo);
        model.addAttribute("boardList", qnaBoard);
        return "/member/qna/read";
    }


}