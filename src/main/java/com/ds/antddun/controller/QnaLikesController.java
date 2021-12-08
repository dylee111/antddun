package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.entity.QnaLikes;
import com.ds.antddun.repository.QnaLikesRepository;
import com.ds.antddun.service.QnaLikesService;
import com.nimbusds.jose.shaded.json.JSONObject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;


@Log4j2
@RestController //@ResponseBody 안붙여도 됨
public class QnaLikesController {

    @Autowired
    private QnaLikesService qnaLikeService;

    @Autowired
    private QnaLikesRepository qnaLikesRepository;


    @PostMapping("/member/qna/{qnaNo}")
    public JSONObject addLikes(@PathVariable Long qnaNo, @AuthenticationPrincipal PrincipalDetails principal)  {

        Long mno = principal.getMember().getMno();
        JSONObject result = new JSONObject();

        QnaLikes target = qnaLikeService.checkLikes(qnaNo, mno);
        if(target != null){ //있으면!!
            qnaLikeService.deleteLikes(qnaNo, mno); //좋아요 해제 (row 삭제)
            result.put("response", null);
            result.put("likesCnt", qnaLikesRepository.countLikes(qnaNo)); //그때 그때 불러서 보여줘야함 (변수 사용 x)
            return result;
        } else {
            qnaLikeService.addLikes(qnaNo, mno);  //좋아요 (row 추가)
            result.put("likesCnt", qnaLikesRepository.countLikes(qnaNo));
            return result;
        }
    }



}