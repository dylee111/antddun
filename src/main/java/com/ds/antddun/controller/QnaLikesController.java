package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.entity.QnaLikes;
import com.ds.antddun.repository.QnaLikesRepository;
import com.ds.antddun.service.QnaLikesService;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;


@Log4j2
@RestController //@ResponseBody 안붙여도 됨
@SessionAttributes("member")
public class QnaLikesController {

    @Autowired
    private QnaLikesService qnaLikeService;

    @Autowired
    private QnaLikesRepository qnaLikesRepository;


    @PostMapping("/qna/{qnaNo}")
    public JSONObject addLikes(@PathVariable Long qnaNo, @AuthenticationPrincipal PrincipalDetails principal)  {

        Long mno = principal.getMember().getMno();
        int likesCnt = qnaLikesRepository.countLikes(qnaNo);
        log.info("likesCnt>>>>>>>>>>>>"+ likesCnt);

        JSONObject result = new JSONObject();

        QnaLikes target = qnaLikeService.checkLikes(qnaNo, mno);
        if(target != null){ //있으면!!
            qnaLikeService.unLikes(qnaNo, mno); //좋아요 해제 (row 삭제)
            result.put("response", null);
            result.put("likesCnt", qnaLikesRepository.countLikes(qnaNo));
            return result;
        } else {
            qnaLikeService.addLikes(qnaNo, mno);  //좋아요 (row 추가)
            result.put("likesCnt", qnaLikesRepository.countLikes(qnaNo));
            return result;
        }
    }


}