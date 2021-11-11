package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.QnaBoardDTO;
import com.ds.antddun.entity.JobList;
import com.ds.antddun.service.JobListService;
import com.ds.antddun.service.QnaService;
import lombok.extern.log4j.Log4j2;
import net.bytebuddy.TypeCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Log4j2
@RequestMapping("/member")
@SessionAttributes("member")
public class QnaBoardController {

    @Autowired
    private JobListService jobListService;

    @Autowired
    private QnaService qnaService;


//    //리스트로 값을 보냄
//    @GetMapping("/qna/list")
//    public String list(Model model) {
//        //직무 카테고리를 보냄
//        List<JobList> jobListDTOList = jobListService.getList();
//        model.addAttribute("jobList", jobListDTOList);
//        //게시글 데이터를 보냄
//        List<QnaBoardDTO> boardDTOList = qnaService.getBoardList();
//        model.addAttribute("postList", boardDTOList);
//        return "/qna/list";
//    }

    //리스트로 값을 보냄
    @GetMapping("/qna/list")
    public String lists(Model model, @PageableDefault(size = 6, sort = "regDate", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("postList", qnaService.getList(pageable));
        return "/qna/list";
    }


    //권한 확인
    @GetMapping("/qna/register")
    public String register(QnaBoardDTO qnaBoardDTO, HttpSession httpSession, HttpServletRequest request) {
        httpSession = request.getSession();
        log.info("httpSession>>>>>>" + httpSession.getId());
        return "/qna/register";
    }

    //글 등록
    @PostMapping("/qna/confirm")
    public String register(QnaBoardDTO qnaBoardDTO, @AuthenticationPrincipal PrincipalDetails principal) {
        qnaService.register(qnaBoardDTO, principal.getMember());
        log.info("REGISTER>>>>>>"+qnaBoardDTO+"/"+principal.getMember());
        return "/qna/list";
    }

    //게시판 조회
    @GetMapping("/qna/read")
    public String read(){
        return "board/view";
    }





}
