package com.ds.antddun.controller;

import com.ds.antddun.config.auth.PrincipalDetails;
import com.ds.antddun.dto.MemberDTO;
import com.ds.antddun.dto.MemberWishListDTO;
import com.ds.antddun.entity.Member;
import com.ds.antddun.entity.MemberWishList;
import com.ds.antddun.service.JobListService;
import com.ds.antddun.service.MemberService;
import com.ds.antddun.service.WishListService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
@Log4j2
public class MemberController {
    @Autowired
    private MemberService memberService;

    @Autowired
    private JobListService jobListService;

    @Autowired
    private WishListService wishListService;


//    @GetMapping("/member/mypage")
//    public String wallet() {
//        return "/member/mypage";
//    }
//    //    @PutMapping("member/updateMember")
//    //    public

    @GetMapping("")
    public String main (Model model, MemberDTO memberDTO, @AuthenticationPrincipal PrincipalDetails principal) {
        if(principal != null) {
            model.addAttribute("wishList", wishListService.getListByMno(principal.getMember().getMno()));
            model.addAttribute("member", principal.getMember());
        }
        return "index";
    }

    @GetMapping("/member/mypage")
    public void userinfo (Model model, @AuthenticationPrincipal PrincipalDetails principal, MemberWishList wishList) {

        Long earn = principal.getMember().getSalary();
        log.info("EARN >> " + earn);
        Double monthly = (earn * 10000 / 12) - (earn * 10000 * 0.009);
        log.info("MONTHLY" + monthly);
        int day = (int) Math.ceil(wishList.getPrice() / (monthly * (wishList.getRate() / 100)));

        if(principal != null) {
            model.addAttribute("member", principal.getMember());
            model.addAttribute("jobList", jobListService.getList());
            model.addAttribute("wishList", wishListService.getListByMno(principal.getMember().getMno()));
            model.addAttribute("dDay",monthly);
        }
//        return "member/mypage";
    }

    @ResponseBody
    @PostMapping("/member/mypage/save")
    public void wishListSave(MemberWishListDTO wishListDTO, @AuthenticationPrincipal PrincipalDetails principalDetails, HttpServletRequest request) {

//        String[] nameArr = request.getParameterValues("wishList");
//        String[] priceArr = request.getParameterValues("price");
//        String[] rateArr = request.getParameterValues("rate");
//            log.info("NAME ARR>>> " + nameArr[0] +"/"+nameArr[1] +"/"+ nameArr[2]);
//            log.info("PRICE ARR>>> " + priceArr[0] +"/"+ priceArr[1] + "/" +priceArr[2]);
//            log.info("RATE ARR>>> " + rateArr[0] +"/"+ rateArr[1] + "/" +rateArr[2]);

        wishListService.register(wishListDTO, principalDetails.getMember());

    }


    @PostMapping("/member/mypage")
    public void wishList(MemberWishListDTO wishListDTO,
                               @AuthenticationPrincipal PrincipalDetails principalDetail) {
        // 동일한 name 속성을 가진 데이터는 배열 형태로 Controller로 넘어온다. split(",")을 이용해 잘라서 사용.'
//        for (int i = 0; i < nameArr.length; i++) {
//            log.info("NAME ARR[" + i + "] >>> " + nameArr[i]);
//            log.info("PRICE ARR[" + i + "] >>> " + priceArr[i]);
//            log.info("RATE ARR[" + i + "] >>> " + rateArr[i]);
//        }
//        wishListService.register(wishListDTO, principalDetails.getMember());
//        return "/member/mypage";
    }

    @GetMapping("/member/messenger")
    public String messenger (Model model, MemberDTO memberDTO, @AuthenticationPrincipal PrincipalDetails principal) {
        if(principal != null) {
            model.addAttribute("member", principal.getMember());
            model.addAttribute("jobList", jobListService.getList());
        }
        return "member/messenger";
    }
}